package com.testesparam;

public class ExceededAttemptsException extends Exception {

    public ExceededAttemptsException(String message) {
        super(message);
    }
}