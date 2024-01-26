package com.chat.controller.exception;

import java.sql.SQLException;

public class ExceptionHandler {
    private static ExceptionHandler exceptionHandler = new ExceptionHandler();

    private ExceptionHandler() {
    }
    public static ExceptionHandler getException(){
        return exceptionHandler;
    }
    public String getMessage(Exception e){
        if (e instanceof SQLException){
            return "SQL Error !";
        } else if (e instanceof NullPointerException) {
            return "Null Error !";
        } else if (e instanceof DuplicateDataException) {
            return "Duplicate Data Error !";
        } else if (e instanceof NoContentException) {
            return "No Content Error !";
        }else {
            return " Please Call Support !";
        }

    }
}
