package com.example.retoconjunto1addi.Pedido;

import com.example.retoconjunto1addi.Items.Item;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa un pedido en la aplicación.
 */
@Data
public class Pedido implements Serializable {
    private Long id;
    private String codigo;
    private Date fecha;
    private Integer usuario;
    private Integer total;

    // Lista de elementos (items) asociados al pedido.
    private ArrayList<Item> items = new ArrayList<>();
}
