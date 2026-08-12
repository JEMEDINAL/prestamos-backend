package com.makers.loan_backend.domain.model;

public class Usuario {
    private Long id;
    private String email;
    private String password;
    private UsuarioRol usuarioRol;

    public Usuario(Long id, String email, String password, UsuarioRol usuarioRol) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.usuarioRol = usuarioRol;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioRol getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(UsuarioRol usuarioRol) {
        this.usuarioRol = usuarioRol;
    }
}
