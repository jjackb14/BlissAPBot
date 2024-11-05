package com.jjackb14.blissapbot.playerlist;

import com.jjackb14.blissapbot.player.Player;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages the List of Players for the BlissAPBot.
 */
public class PlayerList {

    /** ArrayList of Players. */
    private ArrayList<Player> playerList;

    /**
     * Constructs a new PlayerList.
     * Every time a new PlayerList is made it will be an empty TreeSet.
     */
    public PlayerList() {
        newPlayerList();
    }

    /**
     * Creates a new and empty TreeSet that is set to the field playerList.
     */
    public void newPlayerList() {
        this.playerList = new ArrayList<>();
    }

    /**
     * Gets the ArrayList field of playerList.
     * @return The ArrayList of playerList.
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Gets a Player from the PlayerList based on the given IGN.
     * @param ign The player's in-game name.
     * @return The player with the matching IGN.
     */
    public Player getPlayerByIGN(String ign) {
        for (Player player : playerList) {
            if (player.getUserName().equals(ign)) {
                return player;
            }
        }

        return null;
    }

    /**
     * Gets a Player from the PlayerList based on the given Gamtertag.
     * @param tag The player's gamertag.
     * @return The player with the matching gamertag.
     */
    public Player getPlayerByGamtertag(String tag) {
        for (Player player : playerList) {
            if (player.getGamerTag().equals(tag)) {
                return player;
            }
        }

        return null;
    }

    /**
     * Gets a list of Players from the PlayerList based on the map they play on.
     * @param map The given map.
     * @return All the players registered to that map.
     */
    public ArrayList<Player> getPlayersByMap(String map) {
        ArrayList<Player> filtered = new ArrayList<Player>();
        for (Player player : playerList) {
            if (player.getMap().equals(map)) {
                filtered.add(player);
            }
        }

        return filtered;
    }

    /**
     * Gets a list of Players from the PlayerList based on the tribe they are in.
     * @param tribe The given tribe name.
     * @return All the players registered to that tribe.
     */
    public ArrayList<Player> getPlayersByTribe(String tribe) {
        ArrayList<Player> filtered = new ArrayList<Player>();
        for (Player player : playerList) {
            if (player.getTribeName().equals(tribe)) {
                filtered.add(player);
            }
        }

        return filtered;
    }

    /**
     * Gets an Array of the PlayerList for usage in summing AP.
     * @return An Array of the PlayerList.
     */
    public Object[][] getPlayerListAsArray() {
        Object[][] array = new Object[playerList.size()][3];
        for (int i = 0; i < playerList.size(); i++) {
            array[i][0] = playerList.get(i).getUserName();
            array[i][1] = playerList.get(i).getGamerTag();
            array[i][2] = playerList.get(i).getAp();
        }

        return array;
    }
}
