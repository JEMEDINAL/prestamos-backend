package com.makers.loan_backend.infrastructure.dto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class PrestamoDto {
    private Long userId;
    @NotNull(message = "El monto del préstamo es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @Digits(integer = 6, fraction = 2, message = "El formato del dinero es incorrecto son demasiados digitos")
    private BigDecimal amount;

    @Positive(message = "El plazo debe ser un número mayor a cero")
    @Min(value = 5, message = "Debes agregar al menos 5 Cuotas")
    @Max(value = 50, message = "No puedes superar el límite de 50 cuoyas")
    private int plazo;

    public PrestamoDto(Long userId, BigDecimal amount, int plazo) {
        this.userId = userId;
        this.amount = amount;
        this.plazo = plazo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public @NotNull(message = "El monto del préstamo es obligatorio") @Positive(message = "El monto debe ser mayor a cero") @Digits(integer = 6, fraction = 2, message = "El formato del dinero es incorrecto son demasiados digitos") BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "El monto del préstamo es obligatorio") @Positive(message = "El monto debe ser mayor a cero") @Digits(integer = 6, fraction = 2, message = "El formato del dinero es incorrecto son demasiados digitos") BigDecimal amount) {
        this.amount = amount;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }
}
