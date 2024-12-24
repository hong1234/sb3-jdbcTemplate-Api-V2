package com.hong.demo9.exception;

public class ReadyExistException extends RuntimeException {
    public ReadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
