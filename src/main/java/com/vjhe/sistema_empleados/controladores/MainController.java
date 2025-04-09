package com.vjhe.sistema_empleados.controladores;

import com.vjhe.sistema_empleados.modelo.ModelView;
import com.vjhe.sistema_empleados.modelo.Seccion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MainController extends Controller {
    @FXML
    public Button botonSeccionHorarios;
    @FXML
    public Button botonSeccionEmpleado;
    @FXML
    public Button botonSeccionRemuneraciones;


    public void initialize(){
        configurarBotones();
    }

    private void configurarBotones() {
        configurarBotonSeccion(botonSeccionEmpleado, Seccion.NUEVO_EMPLEADO);
        configurarBotonSeccion(botonSeccionRemuneraciones, Seccion.CAMBIAR_REMUNRACIONES);
        configurarBotonSeccion(botonSeccionHorarios, Seccion.ADMINISTRAR_HORARIOS);

    }

    private void configurarBotonSeccion(Button button, Seccion seccion){
        button.setOnAction(event -> {
            model.setTituloSeccionActual(button.getText());
            model.setSeccionActivaProperty(seccion);

        });
    }


}
