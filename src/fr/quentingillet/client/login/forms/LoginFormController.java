package fr.quentingillet.client.login.forms;

import fr.quentingillet.client.ChatClientApplication;
import fr.quentingillet.client.login.ChatLogin;
import fr.quentingillet.client.login.ChatLoginApplication;
import fr.quentingillet.utils.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class LoginFormController {
    private ChatLogin chatLogin;
    private ChatLoginApplication chatLoginApplication;

    public LoginFormController(ChatLoginApplication chatLoginApplication, ChatLogin chatLogin) {
        this.chatLogin = chatLogin;
        this.chatLoginApplication = chatLoginApplication;
    }

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    public void handleLoginButtonAction(ActionEvent actionEvent) {
        Window owner = loginButton.getScene().getWindow();
        if(usernameField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form error", "Enter a username before login.");
            return;
        }
        if(passwordField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form error", "Enter a password before login.");
            return;
        }
        String[] userInformations = chatLogin.loginUser(usernameField.getText(), passwordField.getText());
        if (userInformations != null){
            chatLoginApplication.changeStage(new ChatClientApplication(userInformations).startChatApplication());
        }
        chatLogin.closeConnection();
    }

}
