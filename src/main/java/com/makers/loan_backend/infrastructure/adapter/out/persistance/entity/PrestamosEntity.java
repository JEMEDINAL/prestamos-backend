package com.makers.loan_backend.infrastructure.adapter.out.persistance.entity;

import com.makers.loan_backend.domain.model.PrestamoStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "prestamos")
public class PrestamosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuariosEntity usuario;

    @Column(nullable = false)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrestamoStatus status;

    @Column(nullable = false)
    private int plazo;

    public PrestamosEntity(UsuariosEntity usuario, BigDecimal monto, PrestamoStatus status, int plazo) {
        this.usuario = usuario;
        this.monto = monto;
        this.status = status;
        this.plazo = plazo;
    }


    public PrestamosEntity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuariosEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosEntity usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public PrestamoStatus getStatus() {
        return status;
    }

    public void setStatus(PrestamoStatus status) {
        this.status = status;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }
}

