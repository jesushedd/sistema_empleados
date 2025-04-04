module com.vjhe.sistema_empleados {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.vjhe.sistema_empleados to javafx.fxml;
    exports com.vjhe.sistema_empleados;
}