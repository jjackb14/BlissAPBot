package com.jjackb14.blissapbot.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Exception ExistingPlayerException.
 *
 * @author Jack Benoit
 */
public class ExistingPlayerExceptionTest {

    /**
     * Tests the construction of an ExistingPlayerException with no custom message.
     */
    @Test
    public void testExistingPlayerExceptionNoMessage() {
        Exception e1 = new ExistingPlayerException();
        assertEquals("This player already exists in the AP System.", e1.getMessage());
    }

    /**
     * Tests the construction of an ExistingPlayerException with a custom message.
     */
    @Test
    public void testExistingPlayerExceptionWithMessage() {
        Exception e1 = new ExistingPlayerException("New custom message.");
        assertEquals("New custom message.", e1.getMessage());
    }

}
