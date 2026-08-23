package com.makers.loan_backend.infrastructure.adapter.out.impl;

import com.makers.loan_backend.domain.model.Prestamo;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.entity.PrestamosEntity;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.entity.UsuariosEntity;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.repository.PrestamosRepositorio;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.repository.UsuariosRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class PrestamoImplPuertoTest {
    @Mock
    private PrestamosRepositorio prestamosRepositorio;

    @Mock
    private UsuariosRepositorio usuariosRepositorio;

    @InjectMocks
    private PrestamoImplPuerto prestamoService;



    @BeforeEach
    void setUp() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("usuario@test.com");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
    @Test
    void guardarPrestamo() {
        Prestamo prestamo = new Prestamo(new BigDecimal("1000.00"), 12);
        UsuariosEntity usuarioMock = new UsuariosEntity();
        when(usuariosRepositorio.findByEmail("usuario@test.com"))
                .thenReturn(Optional.of(usuarioMock));
        prestamoService.guardarPrestamo(prestamo);
        verify(usuariosRepositorio, times(1)).findByEmail("usuario@test.com");
        verify(prestamosRepositorio, times(1)).save(any(PrestamosEntity.class));
    }
    @Test
    void guardarPrestamo_UsuarioNoEncontrado_LanzaExcepcion() {
        Prestamo prestamo = new Prestamo(new BigDecimal("1000.00"), 12);
        when(usuariosRepositorio.findByEmail("usuario@test.com"))
                .thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> prestamoService.guardarPrestamo(prestamo));
        verify(prestamosRepositorio, never()).save(any());
    }

    @Test
    void todosPrestamosUsuario() {
    }

    @Test
    void cambiarEstado() {
    }

    @Test
    void eliminarPrestamo() {
    }

    @Test
    void usuariosYPrestamos() {
    }
}