package com.example.retoconjunto1addi.Items;

import com.example.retoconjunto1addi.DBConnection;
import com.example.retoconjunto1addi.Productos.Producto;
import com.example.retoconjunto1addi.Productos.ProductoDAOImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación de la interfaz ItemDAO para acceder a la base de datos y gestionar elementos (items).
 */
public class ItemDAOImp implements ItemDAO {

    private static Connection connection;

    private final static String QUERY_LOADAll = "SELECT * FROM items where codigo=?";

    /**
     * Constructor de la clase ItemDAOImp.
     *
     * @param conn La conexión a la base de datos.
     */
    public ItemDAOImp(Connection conn) {
        connection = conn;
    }

    /**
     * Carga todos los elementos (items) relacionados con un pedido específico desde la base de datos.
     *
     * @param codigoPedido El código del pedido para el cual se cargan los elementos.
     * @return Una lista de objetos Item relacionados con el pedido.
     */
    @Override
    public ArrayList<Item> loadAll(String codigoPedido) {
        ArrayList<Item> exit = new ArrayList<>();
        try (var pst = connection.prepareStatement(QUERY_LOADAll)) {
            pst.setString(1, codigoPedido);
            var rs = pst.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getLong("id"));
                item.setCodigo(rs.getString("codigo"));
                item.setCantidad(rs.getInt("cantidad"));
                Long productid = rs.getLong("product_id");

                // Con esto cargamos el producto en el product_id
                ProductoDAOImp productoDAOImp = new ProductoDAOImp(DBConnection.getConnection());
                Producto producto = productoDAOImp.loadProduct(productid);

                item.setProducto(producto);

                exit.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exit;
    }
}
