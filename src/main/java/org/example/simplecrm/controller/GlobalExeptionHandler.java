package org.example.simplecrm.controller;

import org.example.simplecrm.exceptions.ExceptionBadRequest;
import org.example.simplecrm.exceptions.ExceptionNotFound;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(ExceptionNotFound.class)
    public ResponseEntity<HttpStatus> notFound(){
            return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(ExceptionBadRequest.class)
    public ResponseEntity<HttpStatus> badRequest(){
        return ResponseEntity.badRequest().build();
    }
}
