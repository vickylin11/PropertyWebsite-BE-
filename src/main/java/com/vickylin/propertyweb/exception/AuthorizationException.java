package com.vickylin.propertyweb.exception;

public class AuthorizationException extends Exception {
    public AuthorizationException() {
        super("Please log in in order to perform this action.");
    }
}
