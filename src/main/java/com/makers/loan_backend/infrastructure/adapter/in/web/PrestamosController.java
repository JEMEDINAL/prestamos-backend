package com.makers.loan_backend.infrastructure.adapter.in.web;
import com.makers.loan_backend.application.port.in.PrestamosCasosDeUso;
import com.makers.loan_backend.domain.model.PrestamoStatus;
import com.makers.loan_backend.infrastructure.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/prestamos")
public class PrestamosController {
    private final PrestamosCasosDeUso prestamosCasosDeUso;

    public PrestamosController(PrestamosCasosDeUso prestamosCasosDeUso) {
        this.prestamosCasosDeUso = prestamosCasosDeUso;
    }

    @GetMapping("/my-loans")
    public ResponseEntity<List<PrestamoResponse>> prestamos() {
        return ResponseEntity.status(HttpStatus.OK).body(prestamosCasosDeUso.todosPrestamosUsuario());
    }
    @GetMapping("/users")
     public ResponseEntity<List<UsuarioResponse>> usuariosConSusPrestamos() {
        return ResponseEntity.status(HttpStatus.OK).body(prestamosCasosDeUso.usuariosYPrestamos());
    }
    @PostMapping
    public ResponseEntity<ApiResponse> crearPrestamo(@Valid @RequestBody PrestamoDto prestamoDto){
        prestamosCasosDeUso.crearPrestamo(prestamoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Se ha solicitado tu prestamo correctamente",HttpStatus.CREATED.value()));
    }
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponse> cambiarEstado(@PathVariable Long id, @Valid @RequestBody EstadoDto estadoDto){
        prestamosCasosDeUso.cambiarEstado(id,estadoDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Se ha cambiado el estado correctamente",HttpStatus.OK.value()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarPrestamo(@PathVariable Long id){
        prestamosCasosDeUso.eliminarPrestamo(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Eliminado correctamente",HttpStatus.OK.value()));
    }
}
