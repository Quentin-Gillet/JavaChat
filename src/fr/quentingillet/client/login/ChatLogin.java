package fr.quentingillet.client.login;

import fr.quentingillet.client.utils.SqlLiteDatabaseController;
import org.sqlite.SQLiteConnection;

import java.sql.*;

public class ChatLogin {
    private Connection connection;

    public ChatLogin(){
        this.connection = new SqlLiteDatabaseController().getConnection();
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatLogin login = new ChatLogin();
        boolean exist = login.userExist("test");
        System.out.println(exist);
    }

    public boolean userExist(String username) {
        PreparedStatement request = null;
        ResultSet response = null;
        try {
            request = connection.prepareStatement("SELECT id FROM Users WHERE username = ?");
            request.setString(1, username);
            response = request.executeQuery();
            return response.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String[] loginUser(String username, String password){
        return null;
    }

    private String[] getAllInformationsOfAnUser(String username){
        return null;
    }

}
