package com.jjackb14.blissapbot.database;

import com.jjackb14.blissapbot.player.Player;

import java.sql.*;

public class Database {

    private static Database instance;

    Connection con;

    public Database() {
        createConnection();
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

            Statement stmt = con.createStatement();

            System.out.println("Database Connection Successful");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException();
        }

    }

    public void insertData(Player player) {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO players (user_name, gamertag, map, tribe, ap, seen) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, player.getUserName());
            stmt.setString(2, player.getGamerTag());
            stmt.setString(3, player.getMap());
            stmt.setString(4, player.getTribeName());
            stmt.setInt(5, player.getAp());
            stmt.setInt(6, player.getSeen());
            stmt.executeUpdate();
            System.out.println("Insertion Completed");
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
