package fr.quentingillet.client;

import fr.quentingillet.client.login.ChatLoginApplication;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public Main(){

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new ChatLoginApplication().startLoginApplication();
        primaryStage.show();
    }
}
