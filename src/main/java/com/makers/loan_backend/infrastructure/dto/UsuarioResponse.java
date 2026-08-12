package com.makers.loan_backend.infrastructure.dto;

import java.util.List;

public class UsuarioResponse {
    private Long id;
    private String email;
    private List<PrestamoResponse> prestamos;

    public UsuarioResponse(Long id, String email, List<PrestamoResponse> prestamos) {
        this.id = id;
        this.email = email;
        this.prestamos = prestamos;
    }

    public UsuarioResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PrestamoResponse> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoResponse> prestamos) {
        this.prestamos = prestamos;
    }
}
