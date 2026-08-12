package com.makers.loan_backend.infrastructure.dto;

import com.makers.loan_backend.domain.model.PrestamoStatus;
import jakarta.validation.constraints.NotNull;

public class EstadoDto {

    @NotNull(message = "El estado es obligatorio")
    private PrestamoStatus estado;

    public PrestamoStatus getEstado() { return estado; }
    public void setEstado(PrestamoStatus estado) { this.estado = estado; }
}
