package com.makers.loan_backend.infrastructure.dto;

public class AuthResponse {
    private String username;
    private String message;
    private String jwt;
    private Boolean status;

    public AuthResponse(String username, String message, String jwt, Boolean status) {
        this.username = username;
        this.message = message;
        this.jwt = jwt;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
