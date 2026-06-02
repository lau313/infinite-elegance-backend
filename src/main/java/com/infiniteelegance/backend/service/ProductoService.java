package com.infiniteelegance.backend.service;

import com.infiniteelegance.backend.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto crear(Producto producto);
    Producto buscarPorId(Long id);
    List<Producto> listarActivos();
    List<Producto> listarTodos();
    Producto actualizar(Long id, Producto producto);
    Producto desactivar(Long id);
}

