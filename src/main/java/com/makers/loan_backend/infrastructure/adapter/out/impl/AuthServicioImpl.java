package com.makers.loan_backend.infrastructure.adapter.out.impl;

import com.makers.loan_backend.application.port.in.UsuariosCasosDeUso;
import com.makers.loan_backend.domain.model.UsuarioRol;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.entity.UsuariosEntity;
import com.makers.loan_backend.infrastructure.adapter.out.persistance.repository.UsuariosRepositorio;
import com.makers.loan_backend.infrastructure.dto.LoginDto;
import com.makers.loan_backend.infrastructure.dto.RegisterDto;
import com.makers.loan_backend.infrastructure.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServicioImpl implements UsuariosCasosDeUso {


    private final UsuariosRepositorio usuariosRepositorio;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServicioImpl(UsuariosRepositorio usuariosRepositorio,
                            JwtTokenProvider jwtTokenProvider,
                            PasswordEncoder passwordEncoder,
                            AuthenticationManager authenticationManager) {
        this.usuariosRepositorio = usuariosRepositorio;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public String login(LoginDto loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDTO) {
        //usuariosRepositorio.findByEmail(registerDTO.getEmail()).orElseThrow(RuntimeException::new);

        UsuariosEntity nuevoUsuario = new UsuariosEntity();
        nuevoUsuario.setEmail(registerDTO.getEmail());

        String passwordEncriptada = passwordEncoder.encode(registerDTO.getPassword());
        nuevoUsuario.setPassword(passwordEncriptada);

        nuevoUsuario.setUsuarioRol(UsuarioRol.USUARIO);
        usuariosRepositorio.save(nuevoUsuario);


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerDTO.getEmail(), registerDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);



        return jwtTokenProvider.generateToken(authentication);
    }
}
