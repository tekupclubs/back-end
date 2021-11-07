package com.example.demo.exception;

public class EvenementCustomException extends RuntimeException{
    private static final long serialVersionUID = -291211739734090347L;

    public EvenementCustomException(String message) {
        super(message);
    }

}
