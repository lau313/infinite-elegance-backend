package com.infiniteelegance.backend.service;

import com.infiniteelegance.backend.model.Producto;
import com.infiniteelegance.backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public Producto crear(Producto producto) {
        producto.setActivo(true);
        return productoRepository.save(producto);
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }

    @Override
    public List<Producto> listarActivos() {
        return productoRepository.findByActivoTrue();
    }

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        Producto existente = buscarPorId(id);
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setUrlImagen(producto.getUrlImagen());
        existente.setMaterial(producto.getMaterial());
        existente.setTipo(producto.getTipo());
        return productoRepository.save(existente);
    }

    @Override
    public Producto desactivar(Long id) {
        Producto producto = buscarPorId(id);
        producto.setActivo(false);
        return productoRepository.save(producto);
    }
}
