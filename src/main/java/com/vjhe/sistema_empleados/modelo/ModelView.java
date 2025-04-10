package com.vjhe.sistema_empleados.modelo;

import javafx.beans.property.*;

public class ModelView {
    private SistemaEmpleados sistemaEmpleados;
    private ObjectProperty<Seccion> seccionActiva = new SimpleObjectProperty<>();
    private StringProperty tituloSeccionActual = new SimpleStringProperty();
    private DoubleProperty remuneracion = new SimpleDoubleProperty();
    private DoubleProperty remuneracionExtra = new SimpleDoubleProperty();

    public ModelView(){
        this.sistemaEmpleados = new SistemaEmpleados();
        this.remuneracion.set(sistemaEmpleados.getRemuneracionBase());
        this.remuneracionExtra.set(sistemaEmpleados.getRemuneracionExtraBase());
    }

    public void setSeccionActiva(Seccion seccion) {
        this.seccionActiva.set(seccion);
    }

    public ObjectProperty<Seccion> seccionActivaProperty(){
        return seccionActiva;
    }

    public void setTituloSeccionActual(String tituloSeccionActual) {
        this.tituloSeccionActual.set(tituloSeccionActual);
    }


    public StringProperty tituloSeccionProperty(){
        return tituloSeccionActual;
    }

    public Seccion getSeccionActiva(){
        return seccionActiva.get();
    }

    public double getRemuneracion() {
        return remuneracion.get();
    }

    public DoubleProperty remuneracionProperty() {
        return remuneracion;
    }

    public double getRemuneracionExtra() {
        return remuneracionExtra.get();
    }

    public DoubleProperty remuneracionExtraProperty() {
        return remuneracionExtra;
    }

    public void crearEmpleado(String nombre, String apellido,double remuneracio, double remunreacionExtra){
        sistemaEmpleados.crearEmpleado(nombre,apellido,remuneracio,remunreacionExtra);
        sistemaEmpleados.sincronizarConDB();
    }
}
