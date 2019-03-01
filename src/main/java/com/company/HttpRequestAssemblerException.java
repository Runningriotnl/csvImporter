package com.company;

public class HttpRequestAssemblerException extends RuntimeException {
    public HttpRequestAssemblerException(String message) {
        super(message);
    }

    public HttpRequestAssemblerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpRequestAssemblerException(Throwable cause) {
        super(cause);
    }
}


