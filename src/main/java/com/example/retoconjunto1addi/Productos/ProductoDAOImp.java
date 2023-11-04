package com.example.retoconjunto1addi.Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación de la interfaz ProductoDAO para acceder a la base de datos y gestionar productos.
 */
public class ProductoDAOImp implements ProductoDAO {

    private Connection connection;

    private static final String QUERY_LOAD = "SELECT * FROM Producto where id = ?";

    /**
     * Constructor de la clase ProductoDAOImp.
     *
     * @param conn La conexión a la base de datos.
     */
    public ProductoDAOImp(Connection conn) {
        connection = conn;
    }

    /**
     * Carga un producto relacionado con un usuario específico a partir de su ID.
     *
     * @param userid El ID del usuario para el cual se carga el producto.
     * @return Un objeto Producto relacionado con el usuario, o null si no se encuentra.
     */
    @Override
    public Producto loadProduct(Long userid) {
        Producto producto = null;

        try (var pst = connection.prepareStatement(QUERY_LOAD)){
            pst.setLong(1, userid);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getLong("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getInt("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }
}
