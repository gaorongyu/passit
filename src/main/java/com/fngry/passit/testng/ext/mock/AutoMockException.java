package com.fngry.passit.testng.ext.mock;

public class AutoMockException extends RuntimeException {

    public AutoMockException(String message) {
        super(message);
    }

    public AutoMockException(String message, Throwable cause) {
        super(message, cause);
    }

}
