package com.andriiiiiko;

public class ValueTooLongException extends Exception {

    /**
     * Constructs a new ValueTooLongException with the specified detail message.
     *
     * @param message The detail message.
     */
    public ValueTooLongException(String message) {
        super(message);
    }
}
