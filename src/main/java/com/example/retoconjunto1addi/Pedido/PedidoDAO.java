package com.example.retoconjunto1addi.Pedido;

import java.util.ArrayList;

/**
 * Interfaz que define métodos para la manipulación de pedidos en la base de datos.
 */
public interface PedidoDAO {

    /**
     * Obtiene una lista de pedidos relacionados con un usuario específico a partir de su ID.
     *
     * @param id El ID del usuario para el cual se recuperan los pedidos.
     * @return Una lista de objetos Pedido relacionados con el usuario.
     */
    ArrayList<Pedido> getpedido(Long id);
}