package project;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;


/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class AuthenticationController {

    private Manager manager;
    private MessageDigest digest;
    private ManagerScreen managerScreen;

    @FXML
    private Button confirmButton;

    @FXML
    private PasswordField passwordField;


    public AuthenticationController(Manager manager) {
        this.manager = manager;
        try {
            this.digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Message digest algorithm not found");
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){

        
    }

    @FXML
    public void handleConfirmButton() {
        byte[] hashed = digest.digest(passwordField.getText().getBytes(StandardCharsets.UTF_8));
        String pass = bytesToHex(hashed);
        if (manager.checkAuth(pass)) {
            managerScreen.start();
        } else {
            passwordField.setText("");
            passwordField.setStyle("-fx-border-color: b80000; -fx-border-width: 2");
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void setManagerScreen(ManagerScreen managerScreen) {
        this.managerScreen = managerScreen;
    }
}

