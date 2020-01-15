package fr.quentingillet.client;

import fr.quentingillet.client.forms.ClientFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class ChatClientApplication extends Application {
    private ArrayList<Thread> threads;

    public ChatClientApplication(){

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        for (Thread thread: threads){
            thread.interrupt();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        threads = new ArrayList<Thread>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/ClientForm.fxml"));
        ChatClient client = new ChatClient("localhost", 8585, Integer.toString(new Random().nextInt()));
        Thread clientThread = new Thread(client);
        clientThread.setDaemon(true);
        clientThread.start();
        threads.add(clientThread);
        ClientFormController controller = new ClientFormController(client);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 640, 400);
        primaryStage.setTitle("JavaChat");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        controller.updateListView();
    }
}
