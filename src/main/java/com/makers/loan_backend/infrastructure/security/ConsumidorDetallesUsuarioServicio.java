package com.makers.loan_backend.infrastructure.security;

import com.makers.loan_backend.infrastructure.dto.LoginDto;
import com.makers.loan_backend.infrastructure.dto.RegisterDto;
import com.makers.loan_backend.application.port.in.UsuariosCasosDeUso;
import com.makers.loan_backend.domain.model.UsuarioRol;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.entity.UsuariosEntity;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.repository.UsuariosRepositorio;
import com.makers.loan_backend.infrastructure.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
public class ConsumidorDetallesUsuarioServicio implements UserDetailsService{
    private final UsuariosRepositorio usuariosRepositorio;

    public ConsumidorDetallesUsuarioServicio(UsuariosRepositorio usuariosRepositorio) {
        this.usuariosRepositorio = usuariosRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuariosEntity usuario = usuariosRepositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        String role = "ROLE_" + usuario.getUsuarioRol();

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }




}
