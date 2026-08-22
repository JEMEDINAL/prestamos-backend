package com.makers.loan_backend.infrastructure.adapter.in.web;
import com.makers.loan_backend.application.port.in.PrestamosCasosDeUso;
import com.makers.loan_backend.infrastructure.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/prestamos")
public class PrestamosController {
    private final PrestamosCasosDeUso prestamosCasosDeUso;

    public PrestamosController(PrestamosCasosDeUso prestamosCasosDeUso) {
        this.prestamosCasosDeUso = prestamosCasosDeUso;
    }

    @GetMapping("/my-loans")
    public ResponseEntity<List<PrestamoResponse>> prestamos() {
        System.out.println("me quiero endeudar " + SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(prestamosCasosDeUso.todosPrestamosUsuario());
    }
    @PostMapping
    public ResponseEntity<ApiResponse> crearPrestamo(@Valid @RequestBody PrestamoDto prestamoDto){
        prestamosCasosDeUso.crearPrestamo(prestamoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("bien mi apa no estas en data credito",HttpStatus.CREATED.value()));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponse> cambiarEstado(@PathVariable Long id, @Valid @RequestBody EstadoDto estadoDto){
        prestamosCasosDeUso.cambiarEstado(id,estadoDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("bien mi apa disfrute la platita",HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarPrestamo(@PathVariable Long id){
        prestamosCasosDeUso.eliminarPrestamo(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Eliminado correctamente",HttpStatus.OK.value()));
    }
}
