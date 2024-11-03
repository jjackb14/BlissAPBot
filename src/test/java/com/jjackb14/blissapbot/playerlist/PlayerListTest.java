package com.jjackb14.blissapbot.playerlist;

import static org.junit.jupiter.api.Assertions.*;

import com.jjackb14.blissapbot.player.Player;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class PlayerListTest {

    /**
     * Tests sorting a list of players.
     */
    @Test
    public void testSorting() {
        PlayerList list = new PlayerList();

        Player p1 = new Player("Bulby", "ShawtySLAP", "Island", "NordVPN");
        Player p2 = new Player("Bob", "NotABob", "Island", "Tribe of Bob");
        Player p3 = new Player("Alex", "MysticMan", "Center", "AlexTribe");
        Player p4 = new Player("Feger", "NotFeger", "Scorched", "FakeTribe");

        Player[] expectedSortedPlayers = {p3, p2, p1, p4};

        list.addPlayer(p1);
        list.addPlayer(p2);
        list.addPlayer(p3);
        list.addPlayer(p4);

        for (int i = 0; i < expectedSortedPlayers.length; i++) {
            assertEquals(expectedSortedPlayers[i], list.getPlayerByIndex(i));
        }
    }

}
