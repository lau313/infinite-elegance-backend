package com.infiniteelegance.backend.controller;

import com.infiniteelegance.backend.dto.ProductoRespuestaDTO;
import com.infiniteelegance.backend.dto.ProductoSolicitudDTO;
import com.infiniteelegance.backend.model.Producto;
import com.infiniteelegance.backend.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoRespuestaDTO> crear(@Valid @RequestBody ProductoSolicitudDTO productoSolicitudDTO) {
        return ResponseEntity.ok(ProductoRespuestaDTO.desde(
                productoService.crear(productoSolicitudDTO.aProducto())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoRespuestaDTO> buscarPorId(@PathVariable Long id) {
        Producto producto = productoService.buscarPorId(id);
        if (!producto.isActivo()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductoRespuestaDTO.desde(producto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoRespuestaDTO>> listarActivos() {
        return ResponseEntity.ok(productoService.listarActivos()
                .stream()
                .map(ProductoRespuestaDTO::desde)
                .toList());
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ProductoRespuestaDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos()
                .stream()
                .map(ProductoRespuestaDTO::desde)
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoRespuestaDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoSolicitudDTO productoSolicitudDTO) {
        return ResponseEntity.ok(ProductoRespuestaDTO.desde(
                productoService.actualizar(id, productoSolicitudDTO.aProducto())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoRespuestaDTO> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(ProductoRespuestaDTO.desde(productoService.desactivar(id)));
    }
}
