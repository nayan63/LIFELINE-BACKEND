package com.lifeline.ApiGateway.exceptionhandle;

public class CustomException extends RuntimeException{
    public CustomException(String message)
    {
        super(message);
    }
}
