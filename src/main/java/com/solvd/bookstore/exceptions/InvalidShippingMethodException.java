package com.solvd.bookstore.exceptions;

public class InvalidShippingMethodException extends Exception {
    public InvalidShippingMethodException(String message){
        super(message);
    }
}
