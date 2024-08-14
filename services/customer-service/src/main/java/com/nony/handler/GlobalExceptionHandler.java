package com.nony.handler;

import com.nony.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClassNotFoundException.class)
    // Defines this method as an exception handler for ClassNotFoundException
    public ResponseEntity<String> handle(CustomerNotFoundException customerNotFoundException) { // Handles the specific CustomerNotFoundException
        return ResponseEntity // Returns a ResponseEntity object
                .status(HttpStatus.NOT_FOUND) // Sets the HTTP status to NOT_FOUND (404)
                .body(customerNotFoundException.getMessage()); // Sets the response body to the exception's message
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Defines this method as an exception handler for MethodArgumentNotValidException
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) { // Handles the exception and returns a ResponseEntity with an ErrorResponse
        var errors = new HashMap<String, String>(); // Creates a HashMap to store field errors
        exp.getBindingResult().getAllErrors() // Gets all validation errors from the BindingResult
                .forEach(error -> { // Iterates over each error
                    var fieldName = ((FieldError) error).getField(); // Gets the field name
                    var errorMessage = error.getDefaultMessage(); // Gets the error message
                    errors.put(fieldName, errorMessage); // Puts the field and its error message in the HashMap
                });
        return ResponseEntity // Returns a ResponseEntity object
                .status(HttpStatus.BAD_REQUEST) // Sets the HTTP status to BAD_REQUEST (400)
                .body(new ErrorResponse(errors)); // Creates an ErrorResponse with the collected errors and sets it as the response body
    }

}