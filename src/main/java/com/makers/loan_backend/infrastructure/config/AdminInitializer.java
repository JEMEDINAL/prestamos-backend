package com.makers.loan_backend.infrastructure.config;

import com.makers.loan_backend.domain.model.UsuarioRol;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.entity.UsuariosEntity;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.repository.UsuariosRepositorio;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {
    private final UsuariosRepositorio usuariosRepositorio;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UsuariosRepositorio usuariosRepositorio, PasswordEncoder passwordEncoder) {
        this.usuariosRepositorio = usuariosRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createAdminOnStartup() {

        usuariosRepositorio.save(new UsuariosEntity("admin123@gmail.com",passwordEncoder.encode("admin123"), UsuarioRol.ADMIN));
    }
}
