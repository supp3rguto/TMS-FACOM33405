package com.injdepen;

public class ExceededAttemptsException extends Exception {
    public ExceededAttemptsException(String message) {
        super(message);
    }
}
