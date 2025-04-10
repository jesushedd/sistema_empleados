package com.vjhe.sistema_empleados.controladores;

import javafx.scene.control.TextField;

public class Helpers {
    public static void restringirEntradaNumerica(TextField campo){
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            /*if (newValue.matches("\\d*[\\d.]?\\d*")){
                return;
            } else {
                campo.setText(oldValue);
            }*/
            if (!newValue.matches("\\d*[\\d.]?\\d*")){
                campo.setText(oldValue);
            }
        });
    }

    public static void restringirEntradaAlfabetica(TextField campo){
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^(?!\\s)[\\p{L} '-]*$")) campo.setText(oldValue);
        });
    }
}
