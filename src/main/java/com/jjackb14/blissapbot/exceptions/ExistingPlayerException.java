package com.jjackb14.blissapbot.exceptions;

import java.io.Serial;

/**
 * Creates an exception for if a player already exists within the
 *
 * @author Jack Benoit
 */
public class ExistingPlayerException extends Exception {

    /**
     * Unique Serial Version UID for ExistingPlayerException.
     */
    @Serial
    private static final long serialVersionUID = 6682685211050039039L;

    /**
     * Creates an ExistingPlayerException object using the default message for the exception.
     * This constructor will be called if no message parameter is provided in the exceptions
     * usage.
     */
    public ExistingPlayerException() {
        super("This player already exists in the AP System.");
    }

    /**
     * Creates an ExistingPlayerException object using a custom message provided by the user.
     * This constructor will be called if the user gives a message in the exceptions usage.
     * @param message Custom message provided by the user of ExistingPlayerException.
     */
    public ExistingPlayerException(String message) {
        super(message);
    }
}
