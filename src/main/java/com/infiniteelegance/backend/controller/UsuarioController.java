package com.infiniteelegance.backend.controller;

import com.infiniteelegance.backend.dto.UsuarioRespuestaDTO;
import com.infiniteelegance.backend.model.Usuario;
import com.infiniteelegance.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioRespuestaDTO> registrar(@RequestBody Usuario usuario) {
        if (usuarioService.existeCedula(usuario.getCedula())) {
            return ResponseEntity.badRequest().build();
        }
        if (usuarioService.existeEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(UsuarioRespuestaDTO.desde(usuarioService.registrar(usuario)));
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<UsuarioRespuestaDTO> buscarPorCedula(@PathVariable String cedula) {
        return ResponseEntity.ok(UsuarioRespuestaDTO.desde(usuarioService.buscarPorCedula(cedula)));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRespuestaDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos()
                .stream()
                .map(UsuarioRespuestaDTO::desde)
                .toList());
    }

    @PutMapping("/{cedula}/rol")
    public ResponseEntity<UsuarioRespuestaDTO> cambiarRol(
            @PathVariable String cedula,
            @RequestParam Usuario.Rol nuevoRol) {
        return ResponseEntity.ok(UsuarioRespuestaDTO.desde(usuarioService.cambiarRol(cedula, nuevoRol)));
    }
}