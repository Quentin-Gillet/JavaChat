package fr.quentingillet.client.login;

import fr.quentingillet.utils.PasswordHash;
import fr.quentingillet.utils.SqlLiteDatabaseController;

import java.sql.*;
import java.util.Arrays;

public class ChatLogin {
    private Connection connection;

    public static void main(String[] args) {

    }

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
            closeConnection();
            return false;
        }
    }

    public String[] loginUser(String username, String password){
        if(userExist(username)) {
            PreparedStatement request = null;
            ResultSet response = null;
            try {
                request = connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
                request.setString(1, username);
                response = request.executeQuery();
                String[] userInformations = {"", "", ""};
                String userPassword = null;
                while (response.next()) {
                    userInformations[0] = String.valueOf(response.getInt("id"));
                    userInformations[1] = response.getString("username");
                    userPassword = response.getString("password");
                    userInformations[2] = String.valueOf(response.getDate("member_since"));
                }
                assert userPassword != null;
                if (PasswordHash.validatePassword(password, userPassword)) {
                    return userInformations;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                closeConnection();
                return null;
            }
        }
        return null;
    }

    private String[] getAllInformationsOfAnUser(String username){
        PreparedStatement request = null;
        ResultSet response = null;
        try {
            request = connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
            request.setString(1, username);
            response = request.executeQuery();
            String[] userInformations = {"", "", "", ""};
            while (response.next()) {
                userInformations[0] = String.valueOf(response.getInt("id"));
                userInformations[1] = response.getString("username");
                userInformations[2] = response.getString("password");
                userInformations[3] = String.valueOf(response.getDate("member_since"));
            }
            return userInformations;
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return null;
        }
    }

}
