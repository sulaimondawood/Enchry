package com.dawood.enchry.exception;

public class UserEmailDoesNotExists extends RuntimeException {
    public UserEmailDoesNotExists(String message){
        super(message);
    }
}
