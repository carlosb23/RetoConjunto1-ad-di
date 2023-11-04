package com.example.retoconjunto1addi.Items;

import java.util.ArrayList;

/**
 * Interfaz que define métodos para la manipulación de elementos (items) en la base de datos.
 */
public interface ItemDAO {

    /**
     * Carga todos los elementos (items) relacionados con un pedido específico.
     *
     * @param codigoPedido El código del pedido para el cual se cargan los elementos.
     * @return Una lista de objetos Item relacionados con el pedido.
     */
    public ArrayList<Item> loadAll(String codigoPedido);
}
