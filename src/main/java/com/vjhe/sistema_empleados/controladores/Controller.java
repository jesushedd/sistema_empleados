package com.vjhe.sistema_empleados.controladores;

import com.vjhe.sistema_empleados.modelo.ModelView;

public abstract class Controller {
    protected ModelView model;
    public void setModel(ModelView modelView){
        this.model = modelView;
    }
}
