/**
 *
 */
package project;

import javafx.application.Application;
import javafx.stage.Stage;

public class ManagerApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ManagerScreen managerScreen = new ManagerScreen(primaryStage);
        NewPasswordScreen newPasswordScreen = new NewPasswordScreen(primaryStage, managerScreen.getManager(), managerScreen.getPickler());

        managerScreen.getController().setNewPasswordScreen(newPasswordScreen);
        newPasswordScreen.getController().setManagerScreen(managerScreen);

        managerScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
