package com.jjackb14.blissapbot.exceptions;

import java.io.Serial;

/**
 * Creates an exception for if the Gamertag provided by the player is invalid.
 */
public class InvalidGamertagException extends Exception {

    /**
     * Unique serialVersionUID for the exception.
     */
    @Serial
    private static final long serialVersionUID = -6119901756025306379L;

    /**
     * Creates an InvalidGamertagException with the default message.
     */
    public InvalidGamertagException() {
        super("The gamertag you have entered is invalid. Please make sure your gamertag is 24 characters or less. If you believe this is an " +
                "error please make a support ticket.");
    }

    /**
     * Creates an InvalidGamertagException with a custom message.
     * @param message The custom message.
     */
    public InvalidGamertagException(String message) {
        super(message);
    }

}
