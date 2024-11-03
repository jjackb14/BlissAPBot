package com.jjackb14.blissapbot.playerlist;


import com.jjackb14.blissapbot.player.Player;

import java.util.ArrayList;

/**
 * Manages the List of Players for the BlissAPBot.
 *
 * @author Jack Benoit
 */
public class PlayerList {

    /** ArrayList of Players. */
    private ArrayList<Player> playerList;

    /**
     * Constructs a new PlayerList.
     * Every time a new PlayerList is made it will be an empty ArrayList.
     */
    public PlayerList() {
        newPlayerList();
    }

    /**
     * Creates a new and empty ArrayList that is set to the field playerList.
     */
    public void newPlayerList() {
        this.playerList = new ArrayList<Player>();
    }

    /**
     * Adds a player to the PlayerList.
     * @param player The player to add to the PlayerList.
     */
    public void addPlayer(Player player) {
        //TODO add error checking
        playerList.add(player);
        sort();
    }

    /**
     * Gets a Player from the PlayerList based on its expected index.
     * @param idx Index of the Player desired.
     * @return The player at the index.
     */
    public Player getPlayerByIndex(int idx) {
        //TODO add error checking and potentially change visibility
        return playerList.get(idx);
    }



    /**
     *TODO THIS DOESNT WORK LMAO
     * A private helper method to sort the PlayerList in alphabetical order.
     */
    public void sort() {
        //TODO CHANGE VISIBILITY: method needs to be PRIVATE but will be public for testing!
        playerList.sort(null);
    }
}
