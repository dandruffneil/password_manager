package project;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

public class NewPasswordScreen {
    
    private Stage stage;
    private String title;
    private NewPasswordController controller;
    private Scene scene;
    
    public NewPasswordScreen(Stage stage, Manager manager) throws IOException {
        this.stage = stage;
        title = "Password Manager";

        controller = new NewPasswordController(manager);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPasswordView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        controller.clearText();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public NewPasswordController getController() {
        return this.controller;
    }

    public String getNewPasswordTitle() {
        return this.title;
    }

}
