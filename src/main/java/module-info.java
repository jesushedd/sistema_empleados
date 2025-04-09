module com.vjhe.sistema_empleados {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.vjhe.sistema_empleados to javafx.fxml;
    exports com.vjhe.sistema_empleados;
    exports com.vjhe.sistema_empleados.controladores;
    opens com.vjhe.sistema_empleados.controladores to javafx.fxml;
}