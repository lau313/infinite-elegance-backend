package com.infiniteelegance.backend.controller;

import com.infiniteelegance.backend.config.JwtUtil;
import com.infiniteelegance.backend.model.Usuario;
import com.infiniteelegance.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String cedula = body.get("cedula");
        String password = body.get("password");

        if (cedula == null || password == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Usuario usuario = usuarioService.buscarPorCedula(cedula);

            if (!usuario.isActivo()) {
                return ResponseEntity.status(403).build();
            }

            if (!passwordEncoder.matches(password, usuario.getPassword())) {
                return ResponseEntity.status(401).build();
            }

            String token = jwtUtil.generarToken(cedula, usuario.getRol().name());

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "rol", usuario.getRol().name(),
                    "nombre", usuario.getNombre()
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/recuperar")
    public ResponseEntity<Map<String, String>> recuperar(@RequestBody Map<String, String> body) {
        String cedula = body.get("cedula");
        String email = body.get("email");

        if (cedula == null || email == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Usuario usuario = usuarioService.buscarPorCedula(cedula);

            if (!usuario.getEmail().equalsIgnoreCase(email)) {
                return ResponseEntity.status(401).build();
            }

            return ResponseEntity.ok(Map.of(
                    "mensaje", "Verificación exitosa. Próximamente envío de email."
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }
}