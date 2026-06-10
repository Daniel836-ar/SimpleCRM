package org.example.simplecrm.exceptions;

public class ExceptionBadRequest extends RuntimeException{
    public ExceptionBadRequest(String messege) {
        super(messege);
    }
}
