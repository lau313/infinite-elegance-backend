package com.infiniteelegance.backend.dto;

import com.infiniteelegance.backend.model.MaterialProducto;
import com.infiniteelegance.backend.model.Producto;
import com.infiniteelegance.backend.model.TipoProducto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductoSolicitudDTO {

    @NotBlank
    @Size(max = 120)
    private String nombre;

    @NotBlank
    @Size(max = 1000)
    private String descripcion;

    @NotNull
    @Positive
    private Double precio;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    @Size(max = 500)
    private String urlImagen;

    @NotNull
    private MaterialProducto material;

    @NotNull
    private TipoProducto tipo;

    public Producto aProducto() {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setUrlImagen(urlImagen);
        producto.setMaterial(material);
        producto.setTipo(tipo);
        return producto;
    }
}
