package project;

import java.util.List;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.IOException;

public class Pickler {
    private String filename;

    public Pickler(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void savePasswords(Manager manager) {
        File f = new File(filename);
        try {
            f.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(manager);
            objOut.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("A file save error has occured");
            e.printStackTrace();
        }
    }

    public Manager loadPasswords() {
        Manager manager = new Manager();
        File f = new File(filename);
        try {
            FileInputStream fileIn = new FileInputStream(f);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            manager = (Manager) objIn.readObject();
            fileIn.close();
            objIn.close();
        } catch (IOException e) {
            System.out.println("A file load error has occured");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("A class error has occured");
            e.printStackTrace();
        }

        return manager;
    }
}
