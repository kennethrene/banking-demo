package com.banking.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({NoStockAvailableException.class})
    protected ResponseEntity<Object> stockNotAvailable(Exception e, WebRequest request) {
        return handleExceptionInternal(e, "Stock not available", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({NoOrderCreatedException.class})
    protected ResponseEntity<Object> orderCreationError(Exception e, WebRequest request) {
        return handleExceptionInternal(e, "Error creating the order", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
