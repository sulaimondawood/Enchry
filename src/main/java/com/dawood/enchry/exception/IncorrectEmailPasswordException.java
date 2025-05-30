package com.dawood.enchry.exception;

public class IncorrectEmailPasswordException extends RuntimeException {
    public IncorrectEmailPasswordException(String invalidCredentials) {
        super(invalidCredentials);
    }
}
