package com.infiniteelegance.backend.service;

import com.infiniteelegance.backend.model.Usuario;
import java.util.List;
import com.infiniteelegance.backend.model.Rol;

public interface UsuarioService {

    Usuario registrar(Usuario usuario);
    Usuario buscarPorCedula(String cedula);
    Usuario buscarPorEmail(String email);
    boolean existeCedula(String cedula);
    boolean existeEmail(String email);
    List<Usuario> listarTodos();
    Usuario cambiarRol(String cedula, Rol nuevoRol);
}