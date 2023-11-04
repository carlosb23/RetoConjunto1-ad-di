package com.example.retoconjunto1addi.Usuario;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación de la interfaz UsuarioDAO para acceder a la base de datos y gestionar usuarios.
 */
@Log
public class UsuarioDAOImp implements UsuarioDAO {

    private Connection connection;

    private static final String QUERY_LOAD = "SELECT * FROM usuario WHERE nombre = ? AND contraseña = ?";
    private static final String QUERY_LOAD2 = "SELECT nombre,email FROM Usuario WHERE id = ?";

    /**
     * Constructor de la clase UsuarioDAOImp.
     *
     * @param conn La conexión a la base de datos.
     */
    public UsuarioDAOImp(Connection conn) {
        connection = conn;
    }

    /**
     * Carga un usuario a partir de su nombre de usuario y contraseña.
     *
     * @param nombre El nombre de usuario.
     * @param pass   La contraseña del usuario.
     * @return Un objeto Usuario si se encuentra un usuario con las credenciales proporcionadas, o null si no se encuentra.
     */
    @Override
    public Usuario load(String nombre, String pass) {
        Usuario usuario = null;

        try (var pst = connection.prepareStatement(QUERY_LOAD)) {
            pst.setString(1, nombre);
            pst.setString(2, pass);
            var rs = pst.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setContrasena(rs.getString("contraseña"));
                usuario.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar usuario", e);
        }

        return usuario;
    }

    /**
     * Obtiene datos específicos de un usuario a partir de su ID.
     *
     * @param userId El ID del usuario para el cual se obtienen los datos.
     * @return Un objeto Usuario con los datos obtenidos, o null si no se encuentra.
     */
    public Usuario getdatos(Long userId) {
        Usuario datos = null;

        try (PreparedStatement pst = connection.prepareStatement(QUERY_LOAD2)) {
            pst.setLong(1, userId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                datos = new Usuario();
                datos.setNombre(rs.getString("nombre"));
                datos.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}




