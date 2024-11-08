package com.jjackb14.blissapbot.database;

import com.jjackb14.blissapbot.exceptions.InvalidGamertagException;
import com.jjackb14.blissapbot.exceptions.InvalidNameException;
import com.jjackb14.blissapbot.exceptions.InvalidTribeNameException;
import com.jjackb14.blissapbot.player.Player;

import java.sql.*;
import java.util.ArrayList;

/**
 * Manages the Database for BlissAPBot following the singleton design pattern.
 */
public class Database {

    /** The current instance of the database. */
    private static Database instance;

    /** The connection to the database. */
    private Connection con;

    /**
     * Creates a database object and creates the connection to the MySQL database.
     */
    public Database() {
        createConnection();
    }

    /**
     * Gets the instance of the database.
     * @return The instance of the database.
     */
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    /**
     * Creates the connection to the database.
     * @throws RuntimeException if there are any issues connecting to the database.
     */
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

    /**
     * Adds a player to the database.
     * @param player The player to be added.
     * @throws RuntimeException if there are any issues adding to the database.
     */
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

    /**
     * Removes a player from the database based on their gamertag.
     * @param user_name The players in game name.
     * @param gamertag The players gamertag.
     * @return True if the player is removed and false if they are not.
     * @throws RuntimeException if there are any issues removing that player from the database.
     */
    public boolean removeData(String user_name, String gamertag) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM players WHERE user_name=(?) AND gamertag=(?)");
            stmt.setString(1, user_name);
            stmt.setString(2, gamertag);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Removal Completed");
            }
            else {
                System.out.println("Removal failed. Probably aren't in the DB.");
            }
            stmt.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all the players from the database and returns them in an ArrayList.
     * @return All the players in the database.
     */
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            ResultSet resultSet = con.createStatement().executeQuery("SELECT * FROM players");

            while (resultSet.next()) {
                String user_name = resultSet.getString("user_name");
                String gamertag = resultSet.getString("gamertag");
                String map = resultSet.getString("map");
                String tribe = resultSet.getString("tribe");
                int ap = resultSet.getInt("ap");
                int seen = resultSet.getInt("seen");

                Player player = new Player(user_name, gamertag, map, tribe, ap, seen);
                players.add(player);
            }
            resultSet.close();
            System.out.println("Players fetched.");
        } catch (SQLException | InvalidGamertagException | InvalidNameException | InvalidTribeNameException e) {
            throw new RuntimeException(e);
        }

        return players;
    }

}
