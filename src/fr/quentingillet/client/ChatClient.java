package fr.quentingillet.client;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ChatClient implements Runnable{
    private  BufferedReader serverToClientReader;
    private PrintWriter clientToServerWriter;

    private String userName;
    private Stage window;

    public ObservableList<String> chatLog;

    public ChatClient(String hostname, int port, String userName){
        try {
            Socket socket = new Socket(hostname, port);

            serverToClientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientToServerWriter = new PrintWriter(socket.getOutputStream(), true);
            chatLog = FXCollections.observableArrayList();
            this.userName = userName;
            clientToServerWriter.println(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public void writeToServer(String message){
        this.clientToServerWriter.println("[" + this.userName + "]: " + message);
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String inputFromServer = serverToClientReader.readLine();
                Platform.runLater(() -> chatLog.add(inputFromServer));
            } catch (SocketException e) {
                Platform.runLater(() -> chatLog.add("Error in server"));
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
