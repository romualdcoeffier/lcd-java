package com.viseo.lcd.grid;

/**
 * Exception about grid (bad configuration for example).
 * Simple message to user.
 */
public class GridException extends Exception {

    public GridException(String message) {
        super(message);
    }

}
