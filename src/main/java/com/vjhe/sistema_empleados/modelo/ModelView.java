package com.vjhe.sistema_empleados.modelo;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelView {
    private ObjectProperty<Seccion> seccionActivaProperty = new SimpleObjectProperty<>();
    private StringProperty tituloSeccionActual = new SimpleStringProperty();

    public void setSeccionActivaProperty(Seccion seccion) {
        this.seccionActivaProperty.set(seccion);
    }

    public ObjectProperty<Seccion> seccionActivaProperty(){
        return seccionActivaProperty;
    }

    public void setTituloSeccionActual(String tituloSeccionActual) {
        this.tituloSeccionActual.set(tituloSeccionActual);
    }


    public StringProperty tituloSeccionProperty(){
        return tituloSeccionActual;
    }

    public Seccion getSeccionActiva(){
        return seccionActivaProperty.get();
    }
}
