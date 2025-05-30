package com.dawood.enchry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleEmailDoesNotExists(UserEmailDoesNotExists ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}

