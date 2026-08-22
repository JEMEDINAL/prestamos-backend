package com.makers.loan_backend.infrastructure.adapter.out.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.makers.loan_backend.domain.model.UsuarioRol;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UsuarioRol usuarioRol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PrestamosEntity> prestamos;

    public UsuariosEntity(String email, String password, UsuarioRol usuarioRol) {
        this.email = email;
        this.password = password;
        this.usuarioRol = usuarioRol;
    }


    public UsuariosEntity() {
    }

    public List<PrestamosEntity> getPrestamos() {
        return prestamos;
    }

    public Long getId() {
        return id;
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
