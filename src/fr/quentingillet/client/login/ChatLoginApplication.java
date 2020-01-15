package fr.quentingillet.client.login;

import fr.quentingillet.client.ChatClient;
import fr.quentingillet.client.forms.ClientFormController;
import fr.quentingillet.client.login.forms.LoginFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ChatLoginApplication extends Application {
    public ChatLoginApplication(){

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/LoginForm.fxml"));
        //ChatLogin login = new ChatLogin();
        LoginFormController controller = new LoginFormController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 355, 410);
        primaryStage.setTitle("Chat Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
