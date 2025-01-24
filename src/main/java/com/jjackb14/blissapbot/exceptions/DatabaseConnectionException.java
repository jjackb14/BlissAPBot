package com.jjackb14.blissapbot.exceptions;

import java.io.Serial;

/**
 * Creates an Exception if there are errors connecting to the Database.
 */
public class DatabaseConnectionException extends RuntimeException {

    /**
     * Unique serialVersionUID for the exception.
     */
    @Serial
    private static final long serialVersionUID = -8852921463954360440L;

    /**
     * Creates a DatabaseConnectionException with the specified message.
     * @param message The specified message.
     */
    public DatabaseConnectionException(String message) {
        super(message);
    }

    /**
     * Creates a DatabaseConnectionException with the default message.
     */
    public DatabaseConnectionException() {
        super("There was an error connecting to the Database. Check MYSQL if on PC. Ignore if on MAC");
    }
}
