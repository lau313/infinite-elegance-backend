package com.infiniteelegance.backend.repository;

import com.infiniteelegance.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCedula(String cedula);
    Optional<Usuario> findByCedula(String cedula);
}
