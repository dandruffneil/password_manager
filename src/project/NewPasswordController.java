package project;

import java.security.SecureRandom;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class NewPasswordController {

    private Manager manager;

    private ManagerScreen managerScreen;

    @FXML
    private Button addConfirmButton, cancelButton, generateButton;

    @FXML
    private TextField newWebsiteField, newUsernameField, newPasswordField;

    @FXML
    private Label passwordExists, passwordEmpty, generateLabel;


    public NewPasswordController(Manager manager) {
        this.manager = manager;
    }

    @FXML
    public void initialize(){

        
    }

    @FXML
    public void handleAddConfirm() {
        String password = newPasswordField.getText();
        String username = newUsernameField.getText();
        String website = newWebsiteField.getText();

        if (password.equals("") || username.equals("") || website.equals("")) {
            passwordEmpty.setVisible(true);
        } else if (!manager.checkDuplicate(website, username)) {
            manager.addPassword(password, username, website);
            managerScreen.start();
        } else {
            passwordExists.setVisible(true);
        }

    }

    @FXML
    public void handleCancelButton() {
        managerScreen.start();
    }

    @FXML
    public void handleGenerateButton() {
        SecureRandom r = new SecureRandom();
        String randomPass = "";
        for (int i = 0; i < 12; i++) {
            int ascii = r.nextInt(94) + 33;
            randomPass = randomPass.concat(Character.toString((char) ascii));
        }

        generateLabel.setText(randomPass);
        newPasswordField.setText(randomPass);
    }

    public void setManagerScreen(ManagerScreen managerScreen) {
        this.managerScreen = managerScreen;
    }

    public void clearText() {
        newPasswordField.clear();
        newUsernameField.clear();
        newWebsiteField.clear();
        generateLabel.setText("");
        passwordExists.setVisible(false);
        passwordEmpty.setVisible(false);
    }

}

