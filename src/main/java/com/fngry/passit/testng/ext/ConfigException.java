package com.fngry.passit.testng.ext;

/**
 * config exception
 * @author gaorongyu
 */
public class ConfigException extends RuntimeException {

    public ConfigException() {
        super();
    }

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }

}
