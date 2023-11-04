module com.example.retoconjunto1addi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.example.retoconjunto1addi to javafx.fxml;
    exports com.example.retoconjunto1addi;
    opens com.example.retoconjunto1addi.Pedido to javafx.base;
    exports com.example.retoconjunto1addi.Controllers;
    opens com.example.retoconjunto1addi.Controllers to javafx.fxml;
}