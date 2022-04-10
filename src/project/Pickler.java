package project;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.IOException;

public class Pickler {
    private String filename;
    private String key;

    public Pickler(String filename, String key) {
        this.filename = filename;
        this.key = key;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void savePasswords(Manager manager) {
        File f = new File(filename);
        File encryptedFile = new File("text.encrypted");

        // Write class into file f
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

        // Encrypt file f using AES
        try {
            fileProcessor(Cipher.ENCRYPT_MODE,f,encryptedFile);
            System.out.println("Encrypt success");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        f.delete();
        System.out.println("Passwords saved");
    }

    public Manager loadPasswords() {
        Manager manager = new Manager();
        File f = new File(filename);
        File encryptedFile = new File("text.encrypted");

        if (encryptedFile.exists() && !encryptedFile.isDirectory()) {
            // Decrypt file
            try {
                f.createNewFile();
                fileProcessor(Cipher.DECRYPT_MODE,encryptedFile,f);
                System.out.println("Decrypt success");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                    ex.printStackTrace();
            }

            // Load passwords into manager
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
            f.delete();
            System.out.println("Passwords loaded");
        } else {
            System.out.println("Creating new password profile");
            try {
                encryptedFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating new file");
                e.printStackTrace();
            }
            manager = new Manager();
        }
        
        return manager;
    }

    private void fileProcessor(int cipherMode,File inputFile,File outputFile){
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Pickler pickler = new Pickler("passwords.ser", "neilyangsecret12");
        File inputFile = new File("passwords.ser");
        File encryptedFile = new File("text.encrypted");
        File decryptedFile = new File("decrypted-text.txt");
            
        try {
            pickler.fileProcessor(Cipher.ENCRYPT_MODE,inputFile,encryptedFile);
            pickler.fileProcessor(Cipher.DECRYPT_MODE,encryptedFile,decryptedFile);
            System.out.println("Success");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
                ex.printStackTrace();
        }
    }
}
