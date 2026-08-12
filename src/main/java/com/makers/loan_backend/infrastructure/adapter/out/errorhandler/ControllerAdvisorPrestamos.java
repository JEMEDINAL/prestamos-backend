package com.makers.loan_backend.infrastructure.adapter.out.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisorPrestamos {
    private static final String MESSAGE = "Message";
    private static final String STATUS = "Status";
    private static final String TIMESTAMP = "TimeStamp";


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationException(MethodArgumentNotValidException ex){
        Map<String, String> errorDetails = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            errorDetails.put(((FieldError)error).getField(),error.getDefaultMessage());
            errorDetails.put(STATUS, HttpStatus.BAD_REQUEST.toString());
            errorDetails.put(TIMESTAMP, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}
