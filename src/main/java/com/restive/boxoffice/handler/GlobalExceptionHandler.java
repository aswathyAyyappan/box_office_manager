package com.restive.boxoffice.handler;

import com.restive.boxoffice.exception.AgeCategoryNotFoundException;
import com.restive.boxoffice.exception.ErrorResponse;
import com.restive.boxoffice.exception.InvalidTicketTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    ErrorResponse errorResponse;

    @Autowired
    GlobalExceptionHandler(ErrorResponse errorResponse){
        this.errorResponse = errorResponse;
    };

    @ExceptionHandler(AgeCategoryNotFoundException.class)
    public ResponseEntity<?> handleAgeCategoryNotFoundException(AgeCategoryNotFoundException exception){
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getErrorMessage());
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(InvalidTicketTypeException.class)
    public ResponseEntity<?> handleInvalidTicketTypeException(InvalidTicketTypeException exception){
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getErrorMessage());
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }
}
