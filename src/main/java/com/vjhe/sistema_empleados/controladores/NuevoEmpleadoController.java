package com.vjhe.sistema_empleados.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import static com.vjhe.sistema_empleados.controladores.Helpers.*;


public class NuevoEmpleadoController extends Controller{
    @FXML
    private TextField salarioHoraExtraInput;
    @FXML
    private TextField apellidoInput;
    @FXML
    private TextField salarioHoraInput;
    @FXML
    private TextField nombreInput;

    public void initialize(){
        restringirEntradaNumerica(salarioHoraInput);
        restringirEntradaNumerica(salarioHoraExtraInput);
        restringirEntradaAlfabetica(apellidoInput);
        restringirEntradaAlfabetica(nombreInput);


    }
}
