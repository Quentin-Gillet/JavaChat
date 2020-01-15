package fr.quentingillet.client.forms;

import fr.quentingillet.client.ChatClient;
import fr.quentingillet.client.utils.AlertHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

public class ClientFormController{
    private ChatClient client;

    public ClientFormController(ChatClient client){
        this.client = client;
    }

    @FXML
    private ListView<String> chatListView;

    @FXML
    private TextField inputMessage;

    @FXML
    private Button sendButton;

    @FXML
    public void handleSendButtonAction(ActionEvent actionEvent) {
        Window owner = sendButton.getScene().getWindow();
        if(inputMessage.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error formulaire", "Entrer un message avant d’envoyer.");
            return;
        }
        String messageToSend = inputMessage.getText();
        client.writeToServer(messageToSend);
        inputMessage.clear();
        Platform.runLater(() -> client.chatLog.add("[" + client.getUserName() + "]: " + messageToSend));
    }

    @FXML
    public void updateListView(){
        chatListView.setItems(client.chatLog);
    }
}
