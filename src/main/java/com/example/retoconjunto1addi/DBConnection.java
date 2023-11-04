package com.example.retoconjunto1addi;

import lombok.Getter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Clase que gestiona la conexión a la base de datos MySQL.
 */
public class DBConnection {

    /**
     * -- GETTER --
     *  Obtiene la conexión a la base de datos.
     *
     * @return La conexión a la base de datos.
     */
    @Getter
    private static final Connection connection;

    private static Logger logger;

    static {

        logger = Logger.getLogger(DBConnection.class.getName());

        String url;
        String user;
        String password;

        var cfg = new Properties();
        try {
            cfg.load( App.class.getClassLoader().getResourceAsStream("bbdd.properties"));
            logger.info("Configuración cargada");
            url = "jdbc:mysql://" + cfg.getProperty("host") + ":" + cfg.getProperty("port") + "/" + cfg.getProperty("dbname");
            logger.info(url);
            user = cfg.getProperty("user");
            password = cfg.getProperty("pass");
        } catch (IOException e) {
            logger.severe("Error procesando configuración");
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
