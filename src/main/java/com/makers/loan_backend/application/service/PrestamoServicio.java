package com.makers.loan_backend.application.service;

import com.makers.loan_backend.application.port.in.PrestamosCasosDeUso;
import com.makers.loan_backend.application.port.on.PrestamoRepositorioPuerto;
import com.makers.loan_backend.domain.model.Prestamo;
import com.makers.loan_backend.domain.model.PrestamoStatus;
import com.makers.loan_backend.infrastructure.dto.EstadoDto;
import com.makers.loan_backend.infrastructure.dto.PrestamoDto;
import com.makers.loan_backend.infrastructure.dto.UsuarioResponse;
import org.springframework.stereotype.Service;

@Service
public class PrestamoServicio implements PrestamosCasosDeUso {
    private final PrestamoRepositorioPuerto prestamoRepositorioPuerto;


    public PrestamoServicio(PrestamoRepositorioPuerto prestamoRepositorioPuerto) {
        this.prestamoRepositorioPuerto = prestamoRepositorioPuerto;
    }

    @Override
    public void crearPrestamo(PrestamoDto prestamoDto) {
        Prestamo prestamo = new Prestamo(prestamoDto.getUserId(),prestamoDto.getAmount(),prestamoDto.getPlazo());
        prestamoRepositorioPuerto.guardarPrestamo(prestamo);
    }

    @Override
    public UsuarioResponse todosPrestamosUsuario(long id) {
        return prestamoRepositorioPuerto.todosPrestamosUsuario(id);
    }

    @Override
    public void cambiarEstado(long id, EstadoDto estado) {
        prestamoRepositorioPuerto.cambiarEstado(id,estado);

    }

    @Override
    public void eliminarPrestamo(long id) {
        prestamoRepositorioPuerto.eliminarPrestamo(id);
    }


}

