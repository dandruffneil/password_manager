
package project;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Manager implements Serializable {

    private List<Password> passList;
    private int size;
    private String auth = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
    private static Pickler pickler = new Pickler("passwords.ser", "neilyangsecret12");

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

    public static Manager loadPasswords() {
        return pickler.loadPasswords();
    }

    public boolean checkAuth(String pass) {
        return pass.equals(auth);
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
