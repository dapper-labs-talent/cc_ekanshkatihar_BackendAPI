package com.ek.api.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ek.api.exception.RoleNotFoundException;
import com.ek.api.payload.response.MessageResponse;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<MessageResponse> handleApiException(
    		RoleNotFoundException ex) {
    	MessageResponse response = 
            new MessageResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}