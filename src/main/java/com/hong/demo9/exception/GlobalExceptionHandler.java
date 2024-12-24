package com.hong.demo9.exception;

// import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDetails resourceNotFoundException(ResourceNotFoundException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(HttpStatus.NOT_FOUND);
        errorDetails.setMessage(e.getMessage());
        return errorDetails;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReadyExistException.class)
    public ErrorDetails readyExistException(ReadyExistException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(HttpStatus.BAD_REQUEST);
        errorDetails.setMessage(e.getMessage());
        return errorDetails;
    }

}
