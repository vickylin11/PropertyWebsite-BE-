package com.vickylin.propertyweb.exception;

public class UserPermissionException extends Exception {
    public UserPermissionException() {
        super("You are not allowed to call this endpoint.");
    }
}
