package com.banking.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(NoStockAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNoStockAvailableException(NoStockAvailableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No stock available");
    }
}
