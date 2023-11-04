package com.example.retoconjunto1addi.Items;

import com.example.retoconjunto1addi.Productos.Producto;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un elemento (item) que forma parte de un pedido.
 */
@Data
public class Item implements Serializable {
    private Long id;
    private String codigo;
    private int cantidad;
    private Producto producto;
}
