package com.solvd.bookstore.exceptions;

public class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message){
        super(message);
    }
}
