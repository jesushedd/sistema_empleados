package com.vjhe.sistema_empleados.modelo.entidades;

import java.util.Objects;

public class Empleado {


    static Empleado empleadoTest(String nombre, String apellido){
        final double remuneracionEstandar = 20;
        final double remuneracionExtra = 30;
        return new Empleado(nombre, apellido,remuneracionEstandar, remuneracionExtra );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }



    private Integer id;
    private String nombre;
    private String apellido;
    private double remuneracionHora;
    private double remuneracionHoraExtra;


    public Empleado(String nombre, String apellido, double remuneracion, double remuneracionExtra) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.remuneracionHora = remuneracion;
        this.remuneracionHoraExtra = remuneracionExtra;
    }

    public Empleado(int id, String nombre, String apellido, double remuneracion, double remuneracionExtra) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.remuneracionHora = remuneracion;
        this.remuneracionHoraExtra = remuneracionExtra;
    }

    public Integer getId() {
        return id;
    }

    void setId(int id) {
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

    public double getRemuneracionHora() {
        return remuneracionHora;
    }

    public void setRemuneracionHora(double remuneracionHora) {
        if (remuneracionHora <= 0){
            throw new IllegalArgumentException("Remuneración negativa");
        }
        this.remuneracionHora = remuneracionHora;
    }

    public double getRemuneracionHoraExtra() {
        return remuneracionHoraExtra;
    }

    public void setRemuneracionHoraExtra(double remuneracionHoraExtra) {

        if (remuneracionHoraExtra <= 0){
            throw new IllegalArgumentException("Remuneración negativa");
        }
        this.remuneracionHoraExtra = remuneracionHoraExtra;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", remuneracion=" + remuneracionHora +
                ", remuneracionExtra=" + remuneracionHoraExtra +
                '}';
    }
}
