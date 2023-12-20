package com.andriiiiiko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Main main = new Main();

        try {
            StringUtil methodForTest = new StringUtil();
            String valueForCheck = "<test>";
            main.checkValue(valueForCheck);
            methodForTest.getStringLength(valueForCheck);
        } catch (ValueTooLongException e) {
            logger.error("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Checks the length of the given value and throws an exception if it exceeds 10 characters.
     *
     * @param value The value to be checked.
     * @throws ValueTooLongException If the value length is greater than 10.
     */
    public void checkValue(final String value) throws ValueTooLongException {
        if (value.length() > 10) {
            throw new ValueTooLongException("Value length is greater than 10");
        }
    }
}
