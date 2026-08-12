package com.makers.loan_backend.application.port.on;

import com.makers.loan_backend.domain.model.Prestamo;
import com.makers.loan_backend.domain.model.PrestamoStatus;
import com.makers.loan_backend.infrastructure.dto.EstadoDto;
import com.makers.loan_backend.infrastructure.dto.UsuarioResponse;

public interface PrestamoRepositorioPuerto {
    void guardarPrestamo(Prestamo prestamo);
    UsuarioResponse todosPrestamosUsuario(long id);
    void cambiarEstado(long id, EstadoDto estado);
    void eliminarPrestamo(long id);
}
