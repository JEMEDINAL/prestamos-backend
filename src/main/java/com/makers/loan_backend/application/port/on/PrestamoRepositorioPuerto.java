package com.makers.loan_backend.application.port.on;

import com.makers.loan_backend.domain.model.Prestamo;
import com.makers.loan_backend.infrastructure.dto.EstadoDto;
import com.makers.loan_backend.infrastructure.dto.PrestamoResponse;
import com.makers.loan_backend.infrastructure.dto.UsuarioResponse;

import java.util.List;

public interface PrestamoRepositorioPuerto {
    void guardarPrestamo(Prestamo prestamo);
    List<PrestamoResponse> todosPrestamosUsuario();
    void cambiarEstado(long id, EstadoDto estado);
    void eliminarPrestamo(long id);
}
