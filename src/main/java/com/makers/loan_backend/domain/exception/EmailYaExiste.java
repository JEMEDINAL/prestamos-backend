package com.makers.loan_backend.domain.exception;

public class EmailYaExiste extends RuntimeException{
    public EmailYaExiste() {
        super("ya existe un usuario con este email");
    }
}
