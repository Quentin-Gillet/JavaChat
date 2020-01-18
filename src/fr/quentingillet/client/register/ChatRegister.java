package fr.quentingillet.client.register;

import fr.quentingillet.utils.PasswordHash;
import fr.quentingillet.utils.SqlLiteDatabaseController;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatRegister {
    private Connection connection;

    public ChatRegister(){
        this.connection = new SqlLiteDatabaseController().getConnection();
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String username, String password){
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement("INSERT INTO Users (username, password, member_since) VALUES (?, ?, ?)");
            request.setString(1, username);
            String hashPassword = PasswordHash.generateStrongPasswordHash(password);
            request.setString(2, hashPassword);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            request.setString(3, dtf.format(now));
            request.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return false;
        }
    }

}
