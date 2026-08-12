package com.makers.loan_backend.infrastructure.adapter.out.persistance.repository;

import com.makers.loan_backend.infrastructure.adapter.out.persistance.entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepositorio extends JpaRepository<UsuariosEntity,Long> {
    Optional<UsuariosEntity> findByEmail(String email);
}
