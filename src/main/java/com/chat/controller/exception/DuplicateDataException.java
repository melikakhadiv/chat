package com.chat.controller.exception;

public class DuplicateDataException extends Exception{
    private String message;

    public DuplicateDataException() {
        message = "Duplicate Data!!";
    }

    public DuplicateDataException(String message) {
        this.message = message;
    }
}
