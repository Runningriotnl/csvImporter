package com.company.person;

public class PersonParserException extends RuntimeException {
    public PersonParserException() {
    }

    public PersonParserException(String message) {
        super(message);
    }

    public PersonParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonParserException(Throwable cause) {
        super(cause);
    }
}
