package com.vickylin.propertyweb.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super("Resource not found.");
    }
}
