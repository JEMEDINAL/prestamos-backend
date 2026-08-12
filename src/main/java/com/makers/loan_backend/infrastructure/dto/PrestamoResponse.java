package com.makers.loan_backend.infrastructure.dto;

import com.makers.loan_backend.domain.model.PrestamoStatus;

import java.math.BigDecimal;

public class PrestamoResponse {
    private Long id;
    private BigDecimal monto;
    private PrestamoStatus status;
    private int plazo;


    public PrestamoResponse(Long id, BigDecimal monto, PrestamoStatus status, int plazo) {
        this.id = id;
        this.monto = monto;
        this.status = status;
        this.plazo = plazo;
    }

    public PrestamoResponse() {
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
