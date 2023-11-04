package com.example.retoconjunto1addi.Usuario;

/**
 * Interfaz que define métodos para la manipulación de usuarios en la base de datos.
 */
public interface UsuarioDAO {

    /**
     * Carga un usuario a partir de su nombre de usuario y contraseña.
     *
     * @param user El nombre de usuario.
     * @param pass La contraseña del usuario.
     * @return Un objeto Usuario si se encuentra un usuario con las credenciales proporcionadas, o null si no se encuentra.
     */
    Usuario load(String user, String pass);
}

