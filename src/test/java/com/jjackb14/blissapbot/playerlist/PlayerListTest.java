package com.jjackb14.blissapbot.playerlist;

import com.jjackb14.blissapbot.exceptions.ExistingPlayerException;
import com.jjackb14.blissapbot.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class PlayerListTest {

    @Test
    public void testPlayerList() {
        PlayerList list = new PlayerList();

        try {
            list.addPlayer(new Player("Bulby", "ShawtySLAP", "Island", "NordVPN"));
            list.addPlayer(new Player("Luna", "NotLuna", "Center", "FakeTribe"));
        }
        catch (ExistingPlayerException e) {
            fail("Player already exists in the list.");
        }

        assertEquals(2, list.getPlayerList().size());

        assertEquals("Bulby", list.getPlayerByIGN("Bulby").getUserName());

        assertEquals("Luna", list.getPlayerByGamtertag("NotLuna").getUserName());

        assertEquals(1, list.getPlayersByMap("Center").size());

        assertEquals(1, list.getPlayersByTribe("NordVPN").size());
    }

}
