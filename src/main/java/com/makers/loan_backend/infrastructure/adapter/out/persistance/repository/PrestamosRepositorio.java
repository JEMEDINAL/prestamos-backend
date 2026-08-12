package com.makers.loan_backend.infrastructure.adapter.out.persistance.repository;

import com.makers.loan_backend.infrastructure.adapter.out.persistance.entity.PrestamosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamosRepositorio extends JpaRepository<PrestamosEntity,Long> {
}
