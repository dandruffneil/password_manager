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
        NewPasswordScreen newPasswordScreen = new NewPasswordScreen(primaryStage, managerScreen.getManager());
        AuthenticationScreen authScreen = new AuthenticationScreen(primaryStage, managerScreen.getManager());

        authScreen.getController().setManagerScreen(managerScreen);
        managerScreen.getController().setNewPasswordScreen(newPasswordScreen);
        newPasswordScreen.getController().setManagerScreen(managerScreen);

        authScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
