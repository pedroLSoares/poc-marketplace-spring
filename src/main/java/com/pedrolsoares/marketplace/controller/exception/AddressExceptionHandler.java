package com.pedrolsoares.marketplace.controller.exception;

import com.pedrolsoares.marketplace.exception.AddressNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AddressExceptionHandler {

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<Object> handleAddressNotFound(AddressNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
