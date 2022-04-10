
package project;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Manager implements Serializable {

    private List<Password> passList;
    private int size;
    private String auth = "90061665741568e0db32458b16ecda34c785ef0ac9f377ae80b7b3e3d4f28071";
    private Pickler pickler;

    public Manager() {
        this.passList = new ArrayList<Password>();
        this.size = 0;
    }

    public void addPassword(String password, String username, String website) {
        Password newPass = new Password(password, username, website);
        passList.add(newPass);
        size++;
        Collections.sort(passList);
        pickler.savePasswords(this);
    }

    public void deletePassword(String website, String username) {
        int index = findPasswordIndex(website, username);
        if (index != -1) {
            passList.remove(index);
            size--;
        }
        pickler.savePasswords(this);
    }

    public void editPassword(String website, String username, String newPassword) {
        int index = findPasswordIndex(website, username);
        if (index != -1) {
            passList.get(index).setPassword(newPassword);
        }
        pickler.savePasswords(this);
    }

    public Password getIndexPassword(int index) {
        return this.passList.get(index);
    }

    public Password getPassword(String website, String username) {
        for (Password password : passList) {
            if (password.getWebsite().equals(website) && password.getUsername().equals(username)) {
                return password;
            }
        }
        return null;
    }

    public int getSize() {
        return this.size;
    }

    public boolean checkDuplicate(String website, String username) {
        for (Password password : passList) {
            if (password.getWebsite().equals(website) && password.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private int findPasswordIndex(String website, String username) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (passList.get(i).isEqual(website, username)) {
                index = i;
            }
        }
        return index;
    }

    public Manager loadPasswords(String key) {
        pickler = new Pickler("passwords.ser", key);
        return pickler.loadPasswords();
    }

    public boolean checkAuth(String pass) {
        return pass.equals(auth);
    }

    public void copy(Manager tempManager) {
        this.size = tempManager.getSize();
        
        for (int i = 0; i < this.size; i++) {
            this.passList.add(tempManager.getIndexPassword(i));
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.addPassword("password", "username", "website");
        manager.addPassword("1234", "neil", "github");
        for (int i = 0; i < manager.size; i++) {
            System.out.println(manager.getIndexPassword(i) + "-------");
        }
    }

    

}
