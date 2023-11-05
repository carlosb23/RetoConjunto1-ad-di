package com.example.retoconjunto1addi.Controllers;

import com.example.retoconjunto1addi.App;
import com.example.retoconjunto1addi.Usuario.Usuario;
import com.example.retoconjunto1addi.Usuario.UsuarioDAO;
import com.example.retoconjunto1addi.Usuario.UsuarioDAOImp;
import com.example.retoconjunto1addi.DBConnection;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la pantalla de inicio de sesión (login).
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnacceder;
    @FXML
    private Label info;
    @FXML
    private Label olvicontra;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
         * Esta mejora permite que al presionar la tecla 'Enter' en el campo de contraseña,
         * se simule el clic en el botón de inicio de sesión.
         */
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Simular el clic en el botón de inicio de sesión
                btnacceder.fire();
            }
        });
    }

    /**
     * Valida a los usuarios según su nombre de usuario y contraseña.
     *
     * @param user El nombre de usuario ingresado.
     * @param pass La contraseña ingresada.
     * @return El objeto Usuario si las credenciales son válidas, o null si no coinciden.
     */
    public Usuario validateUser(String user, String pass) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImp(DBConnection.getConnection());
        return usuarioDAO.load(user, pass);
    }

    /**
     * Maneja el evento de inicio de sesión.
     *
     * @param actionEvent Evento de acción que desencadenó el inicio de sesión.
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        Usuario usuario = validateUser(user, pass);
        if (usuario != null) {
            VentanaPrincipalController.setUserId(usuario.getId());
            App.ventanaPrincipal("Views/ventanaPrincipal.fxml");
        } else {
            info.setText("Usuario incorrecto");
            info.setStyle("-fx-background-color: red");
            txtUser.setText("");
            txtPassword.setText("");
        }
    }

    /**
     * Muestra información de recuperación de contraseñas para usuarios.
     * Al hacer clic en el botón "¿Olvidaste tu contraseña?", se mostrará un cuadro de diálogo con información
     * para recuperar contraseñas de usuarios, incluyendo los nombres de usuario y contraseñas asociados.
     *
     * @param event El evento de clic en el botón "¿Olvidaste tu contraseña?".
     */
    @FXML
    public void clickolvidar(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuarios");
        alert.setContentText("Usuario1: Carlos, Contraseña: 1234\n" +
                "Usuario2: Leo, Contraseña: 1234");
        alert.showAndWait();
    }
}