package com.example.retoconjunto1addi;

import com.example.retoconjunto1addi.Controllers.VentanaDatosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación que inicia la interfaz gráfica de usuario (JavaFX).
 */
public class App extends Application {

    public static Stage myStage;

    /**
     * Método principal que inicia la aplicación.
     *
     * @param stage El objeto Stage principal de la aplicación.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 600);
        myStage.setTitle("Login");
        myStage.setScene(scene);
        myStage.show();
    }

    /**
     * Cambia la vista a la ventana principal con el archivo FXML especificado.
     *
     * @param fxml La ruta al archivo FXML de la ventana principal.
     */
    public static void ventanaPrincipal(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            myStage.setTitle("Ventana Pedidos");
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }

    /**
     * Cambia la vista a la ventana de datos con el archivo FXML especificado.
     *
     * @param fxml La ruta al archivo FXML de la ventana de datos.
     */
    public static void ventanaDatos(String fxml){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            myStage.setTitle("Ventana Pedidos");
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }

    /**
     * Método principal de la aplicación que inicia la interfaz gráfica de usuario (JavaFX).
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        launch();
    }
}