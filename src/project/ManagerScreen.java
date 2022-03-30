package project;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

public class ManagerScreen {
    
    private Stage stage;
    private String title;
    private ManagerController controller;
    private Scene scene;
    
    public ManagerScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Password Manager";

        controller = new ManagerController();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        controller.reset();
        controller.updateWebsiteList();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public ManagerController getController() {
        return this.controller;
    }

    // Something here to add passwords from a file


    public String getManagerTitle() {
        return this.title;
    }

    public Manager getManager() {
        return controller.getManager();
    }

    public Pickler getPickler() {
        return controller.getPickler();
    }

}
