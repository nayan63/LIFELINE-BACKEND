package com.lifeline.Doctor.exceptionhandle;

public class CustomException extends RuntimeException{
    public CustomException(String message)
    {
        super(message);
    }
}
