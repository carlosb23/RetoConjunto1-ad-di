package com.example.retoconjunto1addi.Controllers;

import com.example.retoconjunto1addi.App;
import com.example.retoconjunto1addi.DBConnection;
import com.example.retoconjunto1addi.Items.Item;
import com.example.retoconjunto1addi.Items.ItemDAOImp;
import com.example.retoconjunto1addi.SesionData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana de datos de la aplicación.
 */
public class VentanaDatosController implements Initializable {
    @FXML
    private Button volver;
    @FXML
    private TableView<Item> tabledato;
    @FXML
    private TableColumn<Item, String> columid;
    @FXML
    private TableColumn<Item, String> columcodigo;
    @FXML
    private TableColumn<Item, String> columcantidad;
    @FXML
    private TableColumn<Item, String> columproduct;

    private ObservableList<Item> observableListItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //aqui se llama a la tabla cada campo con su correspondiente celda
        columid.setCellValueFactory((fila) -> {
            String id = String.valueOf(fila.getValue().getId());
            return new SimpleStringProperty(id);
        });

        columcodigo.setCellValueFactory((fila) -> {
            String cPedido = fila.getValue().getCodigo();
            return new SimpleStringProperty(cPedido);
        });

        columcantidad.setCellValueFactory((fila) -> {
            String cCantidad = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(cCantidad);
        });

        columproduct.setCellValueFactory((fila) -> {
            String cProducto = String.valueOf(fila.getValue().getProducto());
            return new SimpleStringProperty(cProducto);
        });

        //Aqui es donde se llena la tabla con cada item usando observablelist
        observableListItem = FXCollections.observableArrayList();
        ItemDAOImp itemDAOImp = new ItemDAOImp(DBConnection.getConnection());
        SesionData.setItems(itemDAOImp.loadAll(SesionData.getPedido().getCodigo()));
        observableListItem.addAll(SesionData.getItems());
        tabledato.setItems(observableListItem);
    }

    /**
     * Maneja el evento de regreso a la ventana principal.
     *
     * @param actionEvent Evento de acción que desencadenó el regreso.
     */
    @FXML
    public void btnvolverVP(ActionEvent actionEvent) {
        App.ventanaDatos("Views/ventanaPrincipal.fxml");
    }
}