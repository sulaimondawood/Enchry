package com.dawood.enchry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserEmailDoesNotExists.class)
    public ResponseEntity<ErrorResponse> handleEmailDoesNotExists(UserEmailDoesNotExists ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExists ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<String> handleUserNotFound(UserNotFoundException ex){
        return  ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(IncorrectEmailPasswordException.class)
    public ResponseEntity<String> handleIncorrectEmailPasswordException(IncorrectEmailPasswordException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

