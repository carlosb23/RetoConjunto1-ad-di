package com.example.retoconjunto1addi.Controllers;

import com.example.retoconjunto1addi.App;
import com.example.retoconjunto1addi.DBConnection;
import com.example.retoconjunto1addi.Pedido.Pedido;
import com.example.retoconjunto1addi.Pedido.PedidoDAO;
import com.example.retoconjunto1addi.Pedido.PedidoDAOImp;
import com.example.retoconjunto1addi.Productos.ProductoDAO;
import com.example.retoconjunto1addi.SesionData;
import com.example.retoconjunto1addi.Usuario.Usuario;
import com.example.retoconjunto1addi.Usuario.UsuarioDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana principal de la aplicación.
 */
public class VentanaPrincipalController implements Initializable {

    @javafx.fxml.FXML
    private TableView<Pedido> tablaproduct;
    @javafx.fxml.FXML
    private Label nomuser;
    @javafx.fxml.FXML
    private Label emailuser;
    @javafx.fxml.FXML
    private MenuItem Logout;
    @javafx.fxml.FXML
    private Button btnlogout;
    @javafx.fxml.FXML
    private TableColumn idColumn;
    @javafx.fxml.FXML
    private TableColumn columcodigo;
    @javafx.fxml.FXML
    private TableColumn columnFecha;
    @javafx.fxml.FXML
    private TableColumn columnuser;
    @javafx.fxml.FXML
    private TableColumn columTotal;
    @javafx.fxml.FXML
    private Button btnsalir;
    @javafx.fxml.FXML
    private MenuItem exitmenu;

    // Usuarios según su inicio de sesión
    private static Long userId;

    /**
     * Establece el ID del usuario actualmente logueado.
     *
     * @param userId El ID del usuario.
     */
    public static void setUserId(Long userId) {
        VentanaPrincipalController.userId = userId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Mostrar en el label el usuario que se ha logeado
        UsuarioDAOImp usuarioDAO = new UsuarioDAOImp(DBConnection.getConnection());
        Usuario datos = usuarioDAO.getdatos(userId);

        if (datos != null) {
            String nombreUsuarioActivo = datos.getNombre();
            String emailUsuarioActivo = datos.getEmail();

            nomuser.setText(nombreUsuarioActivo);
            emailuser.setText(emailUsuarioActivo);
        }

        // Mostrar tabla según usuarios
        PedidoDAO pedidoDAO = new PedidoDAOImp(DBConnection.getConnection());
        List<Pedido> listaPedidos = pedidosbdd(userId);

        // Configura las columnas para mostrar los valores de los objetos Pedido.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        columcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnuser.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        columTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        // Agrega los datos a la tabla.
        ObservableList<Pedido> observableList = FXCollections.observableArrayList(listaPedidos);
        tablaproduct.setItems(observableList);

        // Esto es para seleccionar los pedidos en la tabla
        tablaproduct.getSelectionModel().selectedIndexProperty().addListener((observable, oldv, newv) -> {
            seleccionarPedido(tablaproduct.getSelectionModel().getSelectedItem());
        });
    }

    /**
     * Selecciona un pedido en la tabla y almacena la información del pedido seleccionado
     * en la sesión de datos para su posterior visualización en la ventana de detalles.
     *
     * @param p El pedido seleccionado en la tabla.
     */
    private void seleccionarPedido(Pedido p) {
        SesionData.setPedido(p);
        SesionData.setPos(tablaproduct.getSelectionModel().getSelectedIndex());
        App.ventanaDatos("Views/ventana-datos.fxml");
        App.myStage.setTitle("Ventana Detalles");
    }

    /**
     * Recupera una lista de pedidos relacionados con un usuario específico desde la base de datos.
     *
     * @param userId El ID del usuario para el cual se recuperan los pedidos.
     * @return Una lista de objetos Pedido relacionados con el usuario.
     */
    private List<Pedido> pedidosbdd(Long userId) {
        PedidoDAOImp pedidoDAO = new PedidoDAOImp(DBConnection.getConnection());
        return pedidoDAO.getpedido(userId);
    }

    /**
     * Maneja el evento de inicio de sesión.
     *
     * @param actionEvent Evento de acción que desencadenó el inicio de sesión.
     */
    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        App.ventanaPrincipal("Views/login-view.fxml");
        App.myStage.setTitle("Login");
    }

    /**
     * Maneja el evento de salida de la aplicación.
     *
     * @param actionEvent Evento de acción que desencadenó la salida.
     */
    @javafx.fxml.FXML
    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnsalir.getScene().getWindow();
        stage.close();
    }

}