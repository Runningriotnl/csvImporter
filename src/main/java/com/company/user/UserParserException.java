package com.company.user;

public class UserParserException extends RuntimeException {

    public UserParserException() {
    }

    public UserParserException(String message) {
        super(message);
    }

    public UserParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserParserException(Throwable cause) {
        super(cause);
    }
}
