package com.example.retoconjunto1addi;

import com.example.retoconjunto1addi.Items.Item;
import com.example.retoconjunto1addi.Pedido.Pedido;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Clase que gestiona datos de sesión en la aplicación.
 */
public class SesionData {

    private static Integer posicion = null;     // Posición actual en una lista o tabla (puede ser null).
    /**
     * -- GETTER --
     *  Obtiene el pedido actual en la sesión.
     *
     * @return El pedido actual.
     */
    @Getter
    private static Pedido pedido;               // El pedido actual en la sesión.
    /**
     * -- GETTER --
     *  Obtiene la lista de pedidos en la sesión.
     *
     * @return La lista de pedidos.
     */
    @Getter
    private static ArrayList<Pedido> pedidos = new ArrayList<>(); // Lista de pedidos en la sesión.
    /**
     * -- GETTER --
     *  Obtiene la lista de elementos (ítems) en la sesión.
     *
     * @return La lista de elementos (ítems).
     */
    @Getter
    private static ArrayList<Item> items = new ArrayList<>();     // Lista de elementos (ítems) en la sesión.

    /**
     * Obtiene la posición actual en la sesión.
     *
     * @return La posición actual (puede ser null).
     */
    public static Integer getPos() {
        return posicion;
    }

    /**
     * Establece la posición actual en la sesión.
     *
     * @param pos La nueva posición.
     */
    public static void setPos(Integer pos) {
        SesionData.posicion = pos;
    }

    /**
     * Establece el pedido actual en la sesión.
     *
     * @param pedido El nuevo pedido.
     */
    public static void setPedido(Pedido pedido) {
        SesionData.pedido = pedido;
    }

    /**
     * Establece la lista de pedidos en la sesión.
     *
     * @param pedidos La nueva lista de pedidos.
     */
    public static void setPedidos(ArrayList<Pedido> pedidos) {
        SesionData.pedidos = pedidos;
    }

    /**
     * Establece la lista de elementos (ítems) en la sesión.
     *
     * @param items La nueva lista de elementos (ítems).
     */
    public static void setItems(ArrayList<Item> items) {
        SesionData.items = items;
    }
}
