package com.jjackb14.blissapbot.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Player class of the BlissAPBot system.
 *
 * @author Jack Benoit
 */
public class PlayerTest {

    /**
     * Tests the Player constructor for valid values.
     */
    @Test
    public void testValidPlayerConstructor() {
        //Tests reasonable name input.
        Player p1 = new Player("Bulby", "ShawtySLAP", "Island", "NordVPN");
        assertEquals("Bulby", p1.getUserName());
        assertEquals("ShawtySLAP", p1.getGamerTag());
        assertEquals("Island", p1.getMap());
        assertEquals("NordVPN", p1.getTribeName());

        //Tests a name input right at the max char limit for all fields that it is applicable for.
        final String twentyFourCharName = "wwwwwwwwwwwwwwwwwwwwwwww";
        assertEquals(24, twentyFourCharName.length());
        Player p2 = new Player(twentyFourCharName, twentyFourCharName, "Island",
                twentyFourCharName);
        assertEquals(twentyFourCharName, p2.getUserName());
        assertEquals(twentyFourCharName, p2.getGamerTag());
        assertEquals("Island", p2.getMap());
        assertEquals(twentyFourCharName, p2.getTribeName());

        Player p3 = new Player("Luna", "NotLuna", "Aberration", "AdminTribe");
    }

    /**
     * Tests the Player constructor for invalid values.
     */
    @Test
    public void testInvalidPlayerConstruction() {
        //Tests making a player with a null name.
        Exception e1 = assertThrows(IllegalArgumentException.class, () ->
                new Player(null, "ShawtySLAP", "Island", "NordVPN"));
        assertEquals("Error making player.", e1.getMessage());

        //Tests making a player with a blank name.
        Exception e2 = assertThrows(IllegalArgumentException.class, () ->
                new Player("", "ShawtySLAP", "Island", "NordVPN"));
        assertEquals("Error making player.", e2.getMessage());

        //Tests making a player with a null gamertag.
        Exception e3 = assertThrows(IllegalArgumentException.class, () ->
                new Player("Bulby", null, "Island", "NordVPN"));
        assertEquals("Error making player.", e3.getMessage());

        //Tests making a player with a blank gamertag.
        Exception e4 = assertThrows(IllegalArgumentException.class, () ->
                new Player("Bulby", "", "Island", "NordVPN"));
        assertEquals("Error making player.", e4.getMessage());

        //Tests making a player with a null map.
        Exception e5 = assertThrows(IllegalArgumentException.class, () ->
                new Player("Bulby", "ShawtySLAP", null, "NordVPN"));
        assertEquals("Error making player.", e5.getMessage());

        //Tests making a player with a null tribe name.
        Exception e7 = assertThrows(IllegalArgumentException.class, () ->
                new Player("Bulby", "ShawtySLAP", "Island", null));
        assertEquals("Error making player.", e7.getMessage());

        //Tests making a player with a blank tribe name.
        Exception e8 = assertThrows(IllegalArgumentException.class, () ->
                new Player("Bulby", "ShawtySLAP", "Island", ""));
        assertEquals("Error making player.", e8.getMessage());

        //The following tests will be testing based on a variable that has a length of 25
        final String twentyFiveCharName = "wwwwwwwwwwwwwwwwwwwwwwwww";
        assertEquals(25, twentyFiveCharName.length());

        //Tests a name over the max character limit.
        Exception e9 = assertThrows(IllegalArgumentException.class, () ->
                new Player(twentyFiveCharName, "ShawtySLAP", "Island", "NordVPN"));
        assertEquals("Error making player.", e9.getMessage());

        //Tests a gamertag over the max character limit.
        Exception e10 = assertThrows(IllegalArgumentException.class, () ->
                new Player("Bulby", twentyFiveCharName, "Island", "NordVPN"));
        assertEquals("Error making player.", e10.getMessage());

        //Tests a tribe name over the max character limit.
        Exception e11 = assertThrows(IllegalArgumentException.class, () ->
                new Player("Bulby", "ShawtySLAP", "Island", twentyFiveCharName));
        assertEquals("Error making player.", e11.getMessage());
    }

}
