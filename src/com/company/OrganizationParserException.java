package com.company;

public class OrganizationParserException extends RuntimeException {
    public OrganizationParserException(String message) {
        super(message);
    }

    public OrganizationParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrganizationParserException(Throwable cause) {
        super(cause);
    }
}
