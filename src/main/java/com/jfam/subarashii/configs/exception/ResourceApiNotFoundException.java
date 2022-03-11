package com.jfam.subarashii.configs.exception;

public class ResourceApiNotFoundException extends Exception {
    public ResourceApiNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}