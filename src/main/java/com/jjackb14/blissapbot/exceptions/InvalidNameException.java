package com.jjackb14.blissapbot.exceptions;

import java.io.Serial;

/**
 * Creates an exception for if the Username provided by the player is invalid.
 */
public class InvalidNameException extends Exception {

    /**
     * Unique serialVersionUID for the exception.
     */
    @Serial
    private static final long serialVersionUID = 255963874979945978L;

    /**
     * Creates an InvalidNameException with the default message.
     */
    public InvalidNameException() {
        super("The player name you have entered is invalid. Please make sure your name is 24 characters or less. If you believe this is an " +
                "error please make a support ticket.");
    }

    /**
     * Creates an InvalidNameException with a custom message.
     * @param message The custom message.
     */
    public InvalidNameException(String message) {
        super(message);
    }

}
