package com.makers.loan_backend.infrastructure.dto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class PrestamoDto {
    @NotNull(message = "El monto del préstamo es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @Digits(integer = 6, fraction = 2, message = "El formato del dinero es incorrecto son demasiados digitos")
    private BigDecimal amount;

    @Positive(message = "El plazo debe ser un número mayor a cero")
    @Min(value = 5, message = "Debes agregar al menos 5 Cuotas")
    @Max(value = 50, message = "No puedes superar el límite de 50 cuotas")
    private int plazo;

    public PrestamoDto(BigDecimal amount, int plazo) {
        this.amount = amount;
        this.plazo = plazo;
    }

    public PrestamoDto() {
    }

    public @NotNull(message = "El monto del préstamo es obligatorio") @Positive(message = "El monto debe ser mayor a cero") @Digits(integer = 6, fraction = 2, message = "El formato del dinero es incorrecto son demasiados digitos") BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "El monto del préstamo es obligatorio") @Positive(message = "El monto debe ser mayor a cero") @Digits(integer = 6, fraction = 2, message = "El formato del dinero es incorrecto son demasiados digitos") BigDecimal amount) {
        this.amount = amount;
    }

    @Positive(message = "El plazo debe ser un número mayor a cero")
    @Min(value = 5, message = "Debes agregar al menos 5 Cuotas")
    @Max(value = 50, message = "No puedes superar el límite de 50 cuoyas")
    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(@Positive(message = "El plazo debe ser un número mayor a cero") @Min(value = 5, message = "Debes agregar al menos 5 Cuotas") @Max(value = 50, message = "No puedes superar el límite de 50 cuoyas") int plazo) {
        this.plazo = plazo;
    }
}
