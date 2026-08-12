package com.makers.loan_backend.application.port.in;

import com.makers.loan_backend.infrastructure.dto.LoginDto;
import com.makers.loan_backend.infrastructure.dto.RegisterDto;

public interface UsuariosCasosDeUso {
    String login(LoginDto loginDTO);
    String register(RegisterDto loginDTO);
}
