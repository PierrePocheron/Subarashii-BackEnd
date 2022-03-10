package com.jfam.subarashii.configs.exception;

public class CustomErrorMessageException extends Exception {
    public CustomErrorMessageException(String errorMessage) {
        super(errorMessage);
    }
}