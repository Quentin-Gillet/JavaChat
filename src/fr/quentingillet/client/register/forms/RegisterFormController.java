package fr.quentingillet.client.register.forms;

import fr.quentingillet.client.ChatClientApplication;
import fr.quentingillet.client.register.ChatRegister;
import fr.quentingillet.client.register.ChatRegisterApplication;
import fr.quentingillet.utils.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.awt.*;

public class RegisterFormController {
    private ChatRegisterApplication registerApplication;
    private ChatRegister chatRegister;

    public RegisterFormController(ChatRegisterApplication registerApplication, ChatRegister chatRegister){
        this.registerApplication = registerApplication;
        this.chatRegister = chatRegister;
    }

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        Window owner = registerButton.getScene().getWindow();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if(username.isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form error", "Enter a username before registering.");
            return;
        }
        if(password.isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form error", "Enter a password before registering.");
            return;
        }
        if(confirmPassword.isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form error", "Enter a confirmation password before registering.");
            return;
        }
        if(!password.equals(confirmPassword)){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form error", "The two passwords do not match");
            return;
        }
        if(chatRegister.registerUser(username, password)){
            registerApplication.changeToLoginApp();
            chatRegister.closeConnection();
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form error", "An error occurred, try again later");
            return;
        }
    }

    public void handleLoginHyperlink(ActionEvent e){
        registerApplication.changeToLoginApp();
    }

}
