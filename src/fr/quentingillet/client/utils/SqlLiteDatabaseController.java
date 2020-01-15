package fr.quentingillet.client.utils;

import java.sql.*;

public class SqlLiteDatabaseController {

    Connection connection = null;

    public SqlLiteDatabaseController(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MainDatabase.sqlite");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }
}
