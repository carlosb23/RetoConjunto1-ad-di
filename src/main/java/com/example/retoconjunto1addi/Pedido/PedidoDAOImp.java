package com.example.retoconjunto1addi.Pedido;

import com.example.retoconjunto1addi.DBConnection;
import com.example.retoconjunto1addi.Items.ItemDAOImp;
import com.example.retoconjunto1addi.SesionData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación de la interfaz PedidoDAO para acceder a la base de datos y gestionar pedidos.
 */
public class PedidoDAOImp implements PedidoDAO {

    private Connection connection;

    private static final String QUERY_LOAD = "SELECT * FROM pedido where usuario = ?";

    /**
     * Constructor de la clase PedidoDAOImp.
     *
     * @param conn La conexión a la base de datos.
     */
    public PedidoDAOImp(Connection conn) {
        connection = conn;
    }

    /**
     * Obtiene una lista de pedidos relacionados con un usuario específico a partir de su ID.
     *
     * @param id El ID del usuario para el cual se recuperan los pedidos.
     * @return Una lista de objetos Pedido relacionados con el usuario.
     */
    @Override
    public ArrayList<Pedido> getpedido(Long id) {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        try (var pst = connection.prepareStatement(QUERY_LOAD)) {
            ItemDAOImp itemDAOImp = new ItemDAOImp(DBConnection.getConnection());
            pst.setLong(1, id);
            var rs = pst.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getLong("id"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setTotal(rs.getInt("total"));

                pedido.setItems(itemDAOImp.loadAll(pedido.getCodigo()));
                SesionData.setPedido(pedido);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar Pedidos", e);
        }

        return pedidos;
    }
}
