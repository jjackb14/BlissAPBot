package com.jjackb14.blissapbot.exceptions;

import java.io.Serial;

/**
 * Creates an exception for if the Tribe Name is invalid.
 */
public class InvalidTribeNameException extends Exception {

    /**
     * Unique serialVersionUID for the exception.
     */
    @Serial
    private static final long serialVersionUID = -8884809407810149460L;

    /**
     * Creates an InvalidTribeNameException with the default message.
     */
    public InvalidTribeNameException() {
        super("The tribe name you have entered is invalid. Please make sure your tribe name is 24 characters or less. If you believe this is an " +
                "error please make a support ticket.");
    }

    /**
     * Creates an InvalidTribeNameException with a custom message.
     * @param message The custom message.
     */
    public InvalidTribeNameException(String message) {
        super(message);
    }

}
