package com.makers.loan_backend.infrastructure.adapter.in.web;

import com.makers.loan_backend.infrastructure.dto.AuthResponse;
import com.makers.loan_backend.infrastructure.dto.LoginDto;
import com.makers.loan_backend.infrastructure.dto.RegisterDto;
import com.makers.loan_backend.application.port.in.UsuariosCasosDeUso;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    private final UsuariosCasosDeUso usuariosCasosDeUso;

    public UsuarioController(UsuariosCasosDeUso usuariosCasosDeUso) {
        this.usuariosCasosDeUso = usuariosCasosDeUso;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterDto registerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(registerDTO.getEmail(),
                "Registrado correctamente",usuariosCasosDeUso.register(registerDTO),true));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(loginDTO.getEmail(),
                "Registrado correctamente",usuariosCasosDeUso.login(loginDTO),true));
    }
}
