package com.pedrolsoares.marketplace.exception;

public class AddressNotFoundException extends RuntimeException{

    public AddressNotFoundException(String message) {
        super(message);
    }
}
