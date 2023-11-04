package com.example.retoconjunto1addi.Productos;

import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un producto en la aplicación.
 */
@Data
public class Producto implements Serializable {
    private Long id;
    private String nombre;
    private int precio;
    private Integer cantidad;
}
