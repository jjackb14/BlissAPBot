package com.jjackb14.blissapbot.player;

/**
 * Creates a new Player to be stored in the AP System database.
 *
 * @author Jack Benoit
 */
public class Player {

    /**
     * Contains the possible states that a Player can be in determined on if they are currently
     * playing on Bliss or not.
     */
    public enum PlayerStates {

        /** Player is currently active on Bliss. */
        ACTIVE,

        /** Player has been marked as inactive on Bliss. */
        INACTIVE,

        /**
         * Player has reached enough AP to be remembered in the System. They can become active
         * again if they rejoin Bliss and will retain their AP.
         */
        REMEMBERED
    }

    /**
     * Contains all the possible maps that a player on Bliss can be on.
     */
    public enum Maps {

        /** The Island Map. */
        ISLAND,

        /** Scorched Earth Map. */
        SCORCHED,

        /** The Center Map. */
        CENTER,

        /** Aberration Map. */
        ABERRATION
    }

    /** Player's player name on all Bliss servers. */
    private String userName;

    /** Player's GamerTag for the platform they play on. */
    private String gamerTag;

    /** Player's main map that they play on */
    private Maps map;

    /** The name of the Player's tribe that they are in. */
    private String tribeName;

    /** A Players AP. */
    private int ap;

    /** Max length that a Player name, GamerTag, and tribe name can be. Based of ARK's max. */
    private static final int MAX_NAME_LENGTH = 24;

    /** A String constant value for The Island. */
    private static final String ISLAND_NAME = "Island";

    /** A String constant value for Scorched Earth. */
    private static final String SCORCHED_NAME = "Scorched";

    /** A String constant value for The Center. */
    private static final String CENTER_NAME = "Center";

    /** A String constant value for Aberration. */
    private static final String ABERRATION_NAME = "Aberration";

    /**
     * Creates a new Player based on the information given by the user.
     * This is the constructor that will be called on by the bot when the user is registering in
     * the #register channel in the Bliss discord server.
     * @param userName The in-game name of the Player registering.
     * @param gamerTag The GamerTag of the Player registering.
     * @param map The Main map that the Player plays on.
     * @param tribeName The name of the tribe that a player is a member of.
     * @param ap The amount of AP a player has.
     */
    public Player(String userName, String gamerTag, String map, String tribeName, int ap) {
        setUserName(userName);
        setGamerTag(gamerTag);
        setMap(map);
        setTribeName(tribeName);
        setAP(ap);
    }


    /**
     * Creates a new Player based on the information given by the user.
     * This is the constructor that will be called on by the bot when the user is registering in
     * the #register channel in the Bliss discord server.
     * @param userName The in-game name of the Player registering.
     * @param gamerTag The GamerTag of the Player registering.
     * @param map The Main map that the Player plays on.
     * @param tribeName The name of the tribe that a player is a member of.
     */
    public Player(String userName, String gamerTag, String map, String tribeName) {
        this(userName, gamerTag, map, tribeName, 0);
    }

    /**
     * Returns the in-game name of the Player.
     * @return The Player's in-game name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the field userName with the Player's in-game name.
     * @param userName The Player's in-game name.
     */
    public void setUserName(String userName) {
        if (userName == null || "".equals(userName)) {
            throw new IllegalArgumentException("Error making player.");
        }
        if (userName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Error making player.");
        }
        this.userName = userName;
    }

    /**
     * Returns the Player's GamerTag.
     * @return The Player's GamerTag.
     */
    public String getGamerTag() {
        return gamerTag;
    }

    /**
     * Sets the value of the field gamerTag with the Player's GamerTag.
     * @param gamerTag The Player's GamerTag.
     */
    public void setGamerTag(String gamerTag) {
        if (gamerTag == null || "".equals(gamerTag)) {
            throw new IllegalArgumentException("Error making player.");
        }
        if (gamerTag.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Error making player.");
        }
        this.gamerTag = gamerTag;
    }

    /**
     * Returns the Map that a Player mainly plays on.
     * @return The Map a Player mainly plays on.
     */
    public String getMap() {
        if (map.equals(Maps.ISLAND)) {
            return ISLAND_NAME;
        }
        else if (map.equals(Maps.SCORCHED)) {
            return SCORCHED_NAME;
        }
        else if (map.equals(Maps.CENTER)) {
            return CENTER_NAME;
        }
        else if (map.equals(Maps.ABERRATION)) {
            return ABERRATION_NAME;
        }
        else {
            //It is intended that this line will never be reached however it exists in case
            //of a catastrophic failure for debugging purposes.
            throw new IllegalArgumentException("IllegalArgumentException throw in getter, "
                    + "SERIOUS PROBLEM");
        }
    }

    /**
     * Sets the value of the field map with the Map that a Player mainly plays on.
     * @param map The Map a Player mainly plays on.
     */
    public void setMap(String map) {
        if (map == null || "".equals(map)) {
            throw new IllegalArgumentException("Error making player.");
        }
        switch(map) {
            case ISLAND_NAME:
                this.map = Maps.ISLAND;
                break;
            case SCORCHED_NAME:
                this.map = Maps.SCORCHED;
                break;
            case CENTER_NAME:
                this.map = Maps.CENTER;
                break;
            case ABERRATION_NAME:
                this.map = Maps.ABERRATION;
            default:
                throw new IllegalArgumentException("Error making player.");
        }
    }

    /**
     * Gets the name of a Player's tribe.
     * @return The name of a Player's tribe.
     */
    public String getTribeName() {
        return tribeName;
    }

    /**
     * Sets the value of the field tribeName with the name of a Player's tribe.
     * @param tribeName The name of a Player's tribe.
     */
    public void setTribeName(String tribeName) {
        if (tribeName == null || "".equals(tribeName)) {
            throw new IllegalArgumentException("Error making player.");
        }
        if (tribeName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Error making player.");
        }
        this.tribeName = tribeName;
    }

    /**
     * Gets the value of the AP field.
     * @return The value of the AP field.
     */
    public int getAp() {
        return ap;
    }

    /**
     * Sets the value of the AP field.
     * @param ap The value of AP to be set.
     * @throws IllegalArgumentException if AP is less than 1.
     */
    public void setAP(int ap) {
        if (ap < 1) {
            throw new IllegalArgumentException("Error adding AP.");
        }

        this.ap = ap;
    }
}
