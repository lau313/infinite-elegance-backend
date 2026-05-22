package com.infiniteelegance.backend.dto;

import com.infiniteelegance.backend.model.Usuario;
import lombok.Data;

@Data
public class UsuarioRespuestaDTO {

    private String cedula;
    private String nombre;
    private String email;
    private Usuario.Rol rol;
    private boolean activo;

    public static UsuarioRespuestaDTO desde(Usuario usuario) {
        UsuarioRespuestaDTO dto = new UsuarioRespuestaDTO();
        dto.setCedula(usuario.getCedula());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        dto.setActivo(usuario.isActivo());
        return dto;
    }
}