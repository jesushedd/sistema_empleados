package com.vjhe.sistema_empleados.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Jornada implements Comparable<Jornada>{
    private Integer id_jornada;
    private Empleado empleado;
    private LocalDateTime entrada;
    private LocalDateTime salida;

    public Jornada(Empleado empleado, LocalDateTime entrada, LocalDateTime salida) {
        this.empleado = empleado;
        this.entrada = entrada;
        this.salida = salida;
    }

    public Jornada(Integer id_jornada, Empleado empleado, LocalDateTime entrada, LocalDateTime salida) {
        this.id_jornada = id_jornada;
        this.empleado = empleado;
        this.entrada = entrada;
        this.salida = salida;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public void registrarSalida(){
        this.salida = LocalDateTime.now();
    }

    public void setId_jornada(Integer id_jornada) {
        this.id_jornada = id_jornada;
    }

    public Integer getEmpleadoID(){
        return getEmpleado().getId();
    }

    public static Jornada NUEVA_JORNADA(Empleado empleado, LocalDateTime fechaEntrada){
        return new Jornada(empleado, fechaEntrada, null);
    }

    public boolean esActiva(){
        return entrada != null & salida == null;
    }

    @Override
    public int compareTo(Jornada jornada) {
        return this.entrada.compareTo(jornada.entrada);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jornada jornada = (Jornada) o;
        return Objects.equals(id_jornada, jornada.id_jornada) && Objects.equals(entrada, jornada.entrada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_jornada, entrada);
    }

    @Override
    public String toString() {
        return "Jornada{" +
                "id_jornada=" + id_jornada +
                ", empleado=" + empleado +
                ", entrada=" + entrada +
                ", salida=" + salida +
                '}';
    }
}
