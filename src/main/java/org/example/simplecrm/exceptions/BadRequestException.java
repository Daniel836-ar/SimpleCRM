package org.example.simplecrm.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String messege) {
        super(messege);
    }
}
