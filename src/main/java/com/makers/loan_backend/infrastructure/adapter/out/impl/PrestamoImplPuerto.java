package com.makers.loan_backend.infrastructure.adapter.out.impl;

import com.makers.loan_backend.application.port.on.PrestamoRepositorioPuerto;
import com.makers.loan_backend.domain.model.Prestamo;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.entity.PrestamosEntity;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.entity.UsuariosEntity;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.repository.PrestamosRepositorio;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.repository.UsuariosRepositorio;
import com.makers.loan_backend.infrastructure.dto.EstadoDto;
import com.makers.loan_backend.infrastructure.dto.PrestamoResponse;
import com.makers.loan_backend.infrastructure.dto.UsuarioResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoImplPuerto implements PrestamoRepositorioPuerto {
    private final PrestamosRepositorio prestamosRepositorio;
    private final UsuariosRepositorio usuariosRepositorio;

    public PrestamoImplPuerto(PrestamosRepositorio prestamosRepositorio, UsuariosRepositorio usuariosRepositorio) {
        this.prestamosRepositorio = prestamosRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
    }


    @Override
    @Transactional
    @CacheEvict(value = {"prestamos_usuario", "usuarios_y_prestamos"}, allEntries = true)
    public void guardarPrestamo(Prestamo prestamo) {
        UsuariosEntity usuario = usuariosRepositorio.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(RuntimeException::new);
        prestamosRepositorio.save(new PrestamosEntity(usuario,prestamo.getAmount(),prestamo.getStatus(),prestamo.getPlazo()));
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "prestamos_usuario", key = "T(org.springframework.security.core.context.SecurityContextHolder).getContext().getAuthentication().getName()")
    public List<PrestamoResponse> todosPrestamosUsuario() {
        UsuariosEntity usuario = usuariosRepositorio.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(RuntimeException::new);
        return usuario.getPrestamos().stream()
                .map(prestamoEntity -> {
                    PrestamoResponse prestamoDto = new PrestamoResponse();
                    prestamoDto.setId(prestamoEntity.getId());
                    prestamoDto.setMonto(prestamoEntity.getMonto());
                    prestamoDto.setStatus(prestamoEntity.getStatus());
                    prestamoDto.setPlazo(prestamoEntity.getPlazo());
                    return prestamoDto;
                })
                .toList();
    }

    @Override
    @Transactional
    @CacheEvict(value = {"prestamos_usuario", "usuarios_y_prestamos"}, allEntries = true)
    public void cambiarEstado(long id, EstadoDto estado) {
        PrestamosEntity prestamo = prestamosRepositorio.findById(id)
                .orElseThrow(RuntimeException::new);
        prestamo.setStatus(estado.getEstado());
        prestamosRepositorio.save(prestamo);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"prestamos_usuario", "usuarios_y_prestamos"}, allEntries = true)
    public void eliminarPrestamo(long id) {
        PrestamosEntity prestamo = prestamosRepositorio.findById(id).orElseThrow(RuntimeException::new);
        UsuariosEntity usuario = prestamo.getUsuario();
        usuario.getPrestamos().remove(prestamo);
        usuariosRepositorio.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "usuarios_y_prestamos")
    public List<UsuarioResponse> usuariosYPrestamos() {
        List<UsuarioResponse> usuarios = usuariosRepositorio.findAll().stream().map(
                usuarioEntity -> {
                UsuarioResponse usuarioResponse = new UsuarioResponse();
                usuarioResponse.setId(usuarioEntity.getId());
                usuarioResponse.setEmail(usuarioEntity.getEmail());
                List<PrestamoResponse> prestamos = usuarioEntity.getPrestamos().stream().map(
                        prestamoEntity -> {
                            PrestamoResponse prestamoDto = new PrestamoResponse();
                            prestamoDto.setId(prestamoEntity.getId());
                            prestamoDto.setMonto(prestamoEntity.getMonto());
                            prestamoDto.setStatus(prestamoEntity.getStatus());
                            prestamoDto.setPlazo(prestamoEntity.getPlazo());
                            return prestamoDto;
                        }).toList();
                usuarioResponse.setPrestamos(prestamos);
                return usuarioResponse;
                }).toList();
        System.out.println("usuarios en h2 = " + usuarios.size());
        System.out.println("paso por aqui");
        return usuarios;
    }
}
