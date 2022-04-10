package project;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuthenticationScreen {
    
    private Stage stage;
    private String title;
    private AuthenticationController controller;
    private Scene scene;
    
    public AuthenticationScreen(Stage stage, Manager manager) throws IOException {
        this.stage = stage;
        title = "Password Manager";

        controller = new AuthenticationController(manager);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AuthenticationView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public AuthenticationController getController() {
        return this.controller;
    }

    public String getNewPasswordTitle() {
        return this.title;
    }

}
