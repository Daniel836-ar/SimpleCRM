package org.example.simplecrm.controller;

import org.example.simplecrm.exceptions.BadRequestException;
import org.example.simplecrm.exceptions.NoContentException;
import org.example.simplecrm.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequest(BadRequestException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<String> noContent(NoContentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}
