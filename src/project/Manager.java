
package project;

import java.util.HashMap;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public class Manager implements Serializable {

    private List<Password> passList;
    private int size;

    public Manager() {
        this.passList = new ArrayList<Password>();
        this.size = 0;
    }

    public void addPassword(String password, String username, String website) {
        Password newPass = new Password(password, username, website);
        passList.add(newPass);
        size++;
        Collections.sort(passList);
    }

    public void deletePassword(String website, String username) {
        int index = findPasswordIndex(website, username);
        if (index != -1) {
            passList.remove(index);
            size--;
        }
    }

    public void editPassword(String website, String username, String newPassword) {
        int index = findPasswordIndex(website, username);
        if (index != -1) {
            passList.get(index).setPassword(newPassword);
        }
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


    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.addPassword("password", "username", "website");
        manager.addPassword("1234", "neil", "github");
        for (int i = 0; i < manager.size; i++) {
            System.out.println(manager.getIndexPassword(i) + "-------");
        }
    }

    

}
