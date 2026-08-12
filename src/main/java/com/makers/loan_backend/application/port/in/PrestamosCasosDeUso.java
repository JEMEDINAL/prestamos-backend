package com.makers.loan_backend.application.port.in;

import com.makers.loan_backend.infrastructure.dto.EstadoDto;
import com.makers.loan_backend.infrastructure.dto.PrestamoDto;
import com.makers.loan_backend.infrastructure.dto.UsuarioResponse;

public interface PrestamosCasosDeUso {
    void crearPrestamo(PrestamoDto prestamoDto);
    UsuarioResponse todosPrestamosUsuario(long id);
    void cambiarEstado(long id, EstadoDto estado);
    void eliminarPrestamo(long id);
}
