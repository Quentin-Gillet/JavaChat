package fr.quentingillet.client.register;

import fr.quentingillet.client.ChatClient;
import fr.quentingillet.client.forms.ClientFormController;
import fr.quentingillet.client.login.ChatLoginApplication;
import fr.quentingillet.client.register.forms.RegisterFormController;
import fr.quentingillet.utils.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatRegisterApplication {
    private Stage primaryStage;

    public Stage startRegisterApplication() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/RegisterForm.fxml"));
        RegisterFormController controller = new RegisterFormController(this, new ChatRegister());
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root, 355, 410);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Register to JavaChat");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        this.primaryStage = primaryStage;
        return primaryStage;
    }

    public void changeToLoginApp() {
        StageController.changeStage(new ChatLoginApplication().startLoginApplication(), this.primaryStage);
    }
}
