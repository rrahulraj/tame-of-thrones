package com.rahul.geektrust.exceptions;

public class ErrorMessages {

    private ErrorMessages() {
        //restrict instantiation
    }

    public static final String ERROR_ACCESSING_INPUT_FILE = "Could not access input file, please check if the file exists and is readable";

    public static final String INVALID_STRING_IN_INPUT_FILE = "Invalid input in the given file";

    public static final String INVALID_KINGDOM_NAME_IN_INPUT_FILE = "Invalid kingdom name in file";

}
