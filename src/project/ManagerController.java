package project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.BooleanProperty;


/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class ManagerController {

    private Manager manager;

    private NewPasswordScreen newPasswordScreen;
    private BooleanProperty showPasswordToggle;
    private Clipboard clipboard;

    private Password selectedPassword;

    @FXML
    private ListView<String> websiteList, usernameList;

    @FXML
    private Pane newPasswordPane;

    @FXML 
    private AnchorPane passwordBox;

    @FXML
    private Button addButton, changePassButton, deleteButton, copyButton;
    
    @FXML
    private ToggleButton showPasswordButton;

    @FXML
    private TextField passwordText, passwordHiddenText, newPasswordText;
    

    public ManagerController() {
        this.showPasswordToggle = new SimpleBooleanProperty(false);
        this.manager = Manager.loadPasswords();

        // this.time = new Timeline();
        // time.setCycleCount(Animation.INDEFINITE);
        // time.getKeyFrames().add(new KeyFrame(Duration.millis(500),
        //     new EventHandler<ActionEvent>() {
        //         @Override public void handle(ActionEvent event) {
        //             game.tick();
        //         }
        //     }));

    }

    @FXML
    public void initialize(){
        showPasswordButton.selectedProperty().bindBidirectional(showPasswordToggle);
        passwordText.visibleProperty().bind(showPasswordToggle);
        passwordHiddenText.visibleProperty().bind(showPasswordToggle.not());

        clipboard = Clipboard.getSystemClipboard();

        websiteList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                passwordBox.setVisible(false);
                updateUsernameList(newValue);
            }
        });

        usernameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    passwordBox.setVisible(true);
                    showPasswordToggle.set(false);
                    showPasswordButton.setText("Show Password");
                    String website = websiteList.getSelectionModel().getSelectedItem();
                    String username = usernameList.getSelectionModel().getSelectedItem();
    
                    selectedPassword = manager.getPassword(website, username);
    
                    passwordText.setText(selectedPassword.getPassword());
                    passwordHiddenText.setText(selectedPassword.getPassword());
                }
            }
        });

        updateWebsiteList();
    }

    @FXML
    public void handleAddButton() {
        newPasswordScreen.start();
    }

    @FXML
    public void handleChangePassword() {
        String website = selectedPassword.getWebsite();
        String username = selectedPassword.getUsername();
        String newPassword = newPasswordText.getText();
        
        manager.editPassword(website, username, newPassword);
        passwordHiddenText.setText(newPassword);
        passwordText.setText(newPassword);
        selectedPassword.setPassword(newPassword);
        newPasswordText.clear();
    }

    @FXML
    public void handleShowPassword() {
        if (showPasswordButton.selectedProperty().get()) {
            showPasswordButton.setText("Hide Password");
        } else {
            showPasswordButton.setText("Show Password");
        }
    }

    @FXML
    public void handleDeleteButton() {
        String website = selectedPassword.getWebsite();
        String username = selectedPassword.getUsername();

        manager.deletePassword(website, username);
        reset();
        updateWebsiteList();
    }

    @FXML
    public void handleCopyButton() {
        ClipboardContent content = new ClipboardContent();
        content.putString(selectedPassword.getPassword());
        clipboard.setContent(content);
    }

    public void setNewPasswordScreen(NewPasswordScreen newPasswordScreen) {
        this.newPasswordScreen = newPasswordScreen;
    }

    public Manager getManager() {
        return this.manager;
    }

    public void updateWebsiteList() {
        websiteList.getItems().clear();
        String prev = "";
        for (int i = 0; i < manager.getSize(); i++) {
            Password password = manager.getIndexPassword(i);
            if (!password.getWebsite().equals(prev)) {
                websiteList.getItems().add(password.getWebsite());
            }
            prev = password.getWebsite();
        }
    }

    public void updateUsernameList(String website) {
        usernameList.getItems().clear();
        for (int i = 0; i < manager.getSize(); i++) {
            Password password = manager.getIndexPassword(i);
            if (password.getWebsite().equals(website)) {
                usernameList.getItems().add(password.getUsername());
            }
        }
    }

    public void reset() {
        websiteList.getItems().clear();
        usernameList.getItems().clear();
        passwordBox.setVisible(false);
        showPasswordToggle.set(false);
        showPasswordButton.setText("Show Password");
    }


}

