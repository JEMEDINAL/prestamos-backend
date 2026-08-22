package com.makers.loan_backend.domain.model;

import java.math.BigDecimal;

public class Prestamo {
    private BigDecimal amount;
    private PrestamoStatus status;
    private int plazo;

    public Prestamo(BigDecimal amount,int plazo) {

        this.amount = amount;
        this.status = PrestamoStatus.PENDIENTE;
        this.plazo = plazo;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
