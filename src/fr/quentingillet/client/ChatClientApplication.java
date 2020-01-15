package fr.quentingillet.client;

import fr.quentingillet.client.forms.ClientFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ChatClientApplication {
    private ArrayList<Thread> threads;
    private String[] userInformations;

    public ChatClientApplication(String[] userInformations){
        this.userInformations = userInformations;
    }

    public Stage startChatApplication() {
        threads = new ArrayList<Thread>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/ClientForm.fxml"));
        ChatClient client = new ChatClient("localhost", 8585, userInformations[1]);
        Thread clientThread = new Thread(client);
        clientThread.setDaemon(true);
        clientThread.start();
        threads.add(clientThread);
        ClientFormController controller = new ClientFormController(client);
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root, 640, 400);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("JavaChat");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        controller.updateListView();
        return primaryStage;
    }
}
