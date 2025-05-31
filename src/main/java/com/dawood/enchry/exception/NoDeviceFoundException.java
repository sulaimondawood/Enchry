package com.dawood.enchry.exception;

public class NoDeviceFoundException extends RuntimeException  {
    public NoDeviceFoundException(String deviceDoesNotExist) {
        super(deviceDoesNotExist);
    }
}
