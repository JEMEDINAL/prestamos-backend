package com.makers.loan_backend.application.port.in;

import com.makers.loan_backend.infrastructure.dto.EstadoDto;
import com.makers.loan_backend.infrastructure.dto.PrestamoDto;
import com.makers.loan_backend.infrastructure.dto.PrestamoResponse;

import java.util.List;

public interface PrestamosCasosDeUso {
    void crearPrestamo(PrestamoDto prestamoDto);
    List<PrestamoResponse> todosPrestamosUsuario();
    void cambiarEstado(long id, EstadoDto estado);
    void eliminarPrestamo(long id);
}
