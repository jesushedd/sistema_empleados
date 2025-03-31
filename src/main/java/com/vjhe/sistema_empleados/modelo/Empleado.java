package com.vjhe.sistema_empleados.modelo;

public class Empleado {

    private static double remuneracionBase = 20.0;
    private static double remuneracionExtraBase = 30.0;

    public static Empleado EMPLEADO_BASE(String nombre, String apellido){
        return new Empleado(nombre, apellido,remuneracionBase, remuneracionExtraBase );
    }

    public static void setRemunuracionBase(double remuneracionBase){
        if (remuneracionBase <= 0){
            throw new IllegalArgumentException("Remuneración negativa");
        }
        Empleado.remuneracionBase = remuneracionBase;
    }

    public static void setRemuneracionExtraBase(double remuneracionExtraBase){
        if (remuneracionExtraBase <= 0){
            throw new IllegalArgumentException("Remuneración negativa");
        }
        Empleado.remuneracionExtraBase =remuneracionExtraBase;
    }

    private int id;
    private String nombre;
    private String apellido;
    private double remuneracion;
    private double remuneracionExtra;


    public Empleado(String nombre, String apellido, double remuneracion, double remuneracionExtra) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.remuneracion = remuneracion;
        this.remuneracionExtra = remuneracionExtra;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(double remuneracion) {
        if (remuneracion <= 0){
            throw new IllegalArgumentException("Remuneración negativa");
        }
        this.remuneracion = remuneracion;
    }

    public double getRemuneracionExtra() {
        return remuneracionExtra;
    }

    public void setRemuneracionExtra(double remuneracionExtra) {

        if (remuneracionExtra <= 0){
            throw new IllegalArgumentException("Remuneración negativa");
        }
        this.remuneracionExtra = remuneracionExtra;
    }
}
