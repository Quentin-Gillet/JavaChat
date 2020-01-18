package fr.quentingillet.client.login;

import fr.quentingillet.client.ChatClient;
import fr.quentingillet.client.ChatClientApplication;
import fr.quentingillet.client.forms.ClientFormController;
import fr.quentingillet.client.login.forms.LoginFormController;
import fr.quentingillet.client.register.ChatRegister;
import fr.quentingillet.client.register.ChatRegisterApplication;
import fr.quentingillet.utils.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ChatLoginApplication{
    private Stage primaryStage;

    public Stage startLoginApplication() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/LoginForm.fxml"));
        LoginFormController controller = new LoginFormController(this, new ChatLogin());
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
        primaryStage.setTitle("Chat Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        this.primaryStage = primaryStage;
        return primaryStage;
    }

    public void changeToRegister(){
        StageController.changeStage(new ChatRegisterApplication().startRegisterApplication(), this.primaryStage);
    }

    public void changeToChat(String[] userInformations){
        StageController.changeStage(new ChatClientApplication(userInformations).startChatApplication(), this.primaryStage);
    }

}
