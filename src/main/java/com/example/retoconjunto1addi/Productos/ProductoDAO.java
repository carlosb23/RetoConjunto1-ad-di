package com.example.retoconjunto1addi.Productos;

/**
 * Interfaz que define métodos para la manipulación de productos en la base de datos.
 */
public interface ProductoDAO {

    /**
     * Carga un producto relacionado con un usuario específico a partir de su ID.
     *
     * @param userid El ID del usuario para el cual se carga el producto.
     * @return Un objeto Producto relacionado con el usuario.
     */
    public Producto loadProduct(Long userid);
}
