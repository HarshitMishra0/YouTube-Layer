package com.example.securedYTSpace.ExceptionHandeling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(com.example.securedYTSpace.ExceptionHandeling.ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> resourceNotFoundException(com.example.securedYTSpace.ExceptionHandeling.ResourceNotFoundException ex, WebRequest request) {
        CustomErrorResponse errorDetails = new CustomErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        CustomErrorResponse errorDetails = new CustomErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
