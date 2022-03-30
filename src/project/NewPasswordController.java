package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.ColumnConstraints;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;


/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class NewPasswordController {

    private Manager manager;
    private Pickler pickler;
    private Timeline time;

    private ManagerScreen managerScreen;

    @FXML
    private Button addConfirmButton;

    @FXML
    private TextField newWebsiteField, newUsernameField, newPasswordField;

    @FXML
    private Label passwordExists, passwordEmpty;


    public NewPasswordController(Manager manager, Pickler pickler) {
        this.manager = manager;
        this.pickler = pickler;

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

    public void setManagerScreen(ManagerScreen managerScreen) {
        this.managerScreen = managerScreen;
    }

    public void clearText() {
        newPasswordField.clear();
        newUsernameField.clear();
        newWebsiteField.clear();
        passwordExists.setVisible(false);
        passwordEmpty.setVisible(false);
    }

}

