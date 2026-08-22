package com.makers.loan_backend.infrastructure.adapter.out.errorhandler;

import com.makers.loan_backend.domain.exception.EmailYaExiste;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
    private static final String MESSAGE = "message";
    private static final String STATUS = "Status";
    private static final String TIMESTAMP = "TimeStamp";


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationException(MethodArgumentNotValidException ex){
        Map<String, String> errorDetails = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            errorDetails.put(MESSAGE,error.getDefaultMessage());
            errorDetails.put(STATUS, HttpStatus.BAD_REQUEST.toString());
            errorDetails.put(TIMESTAMP, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> manejarCredencialesIncorrectas(BadCredentialsException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", HttpStatus.UNAUTHORIZED.value());
        errorBody.put("error", "Unauthorized");
        errorBody.put("message", "Correo electrónico o contraseña incorrectos.");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);
    }

    @ExceptionHandler(EmailYaExiste.class)
    public ResponseEntity<Map<String,String>> manejarCredencialesIncorrectas(EmailYaExiste ex) {
        Map<String,String> errorDetails = new HashMap<>();
        errorDetails.put(MESSAGE,ex.getMessage());
        errorDetails.put(STATUS, HttpStatus.CONFLICT.toString());
        errorDetails.put(TIMESTAMP, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }
}
