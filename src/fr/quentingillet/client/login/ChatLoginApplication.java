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
    private ChatLogin chatLogin;
    private Stage mainStage;

    public ChatLoginApplication(){

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.mainStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/LoginForm.fxml"));
        chatLogin = new ChatLogin();
        LoginFormController controller = new LoginFormController(this, chatLogin);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 355, 410);
        mainStage.setTitle("Chat Login");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public void changeStage(Stage stage){
        mainStage.close();
        stage.show();
    }

}
