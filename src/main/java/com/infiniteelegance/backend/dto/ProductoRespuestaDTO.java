package com.infiniteelegance.backend.dto;

import com.infiniteelegance.backend.model.MaterialProducto;
import com.infiniteelegance.backend.model.Producto;
import com.infiniteelegance.backend.model.TipoProducto;
import lombok.Data;

@Data
public class ProductoRespuestaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String urlImagen;
    private MaterialProducto material;
    private TipoProducto tipo;
    private boolean activo;

    public static ProductoRespuestaDTO desde(Producto producto) {
        ProductoRespuestaDTO dto = new ProductoRespuestaDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setUrlImagen(producto.getUrlImagen());
        dto.setMaterial(producto.getMaterial());
        dto.setTipo(producto.getTipo());
        dto.setActivo(producto.isActivo());
        return dto;
    }
}
