package com.vjhe.sistema_empleados.modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Jornada implements Comparable<Jornada>{
    public static final int DURACION_ESTANDAR = 8;

    private Integer id_jornada;
    private Empleado empleado;
    private LocalDateTime entrada;
    private LocalDateTime salida;
    private double remuneracionJornada;

    public Jornada(Empleado empleado, LocalDateTime entrada, LocalDateTime salida) {
        if (empleado == null || entrada == null){
            throw new IllegalArgumentException("Empleado ni entrada pueden ser null");
        }

        this.empleado = empleado;
        this.entrada = entrada.truncatedTo(ChronoUnit.SECONDS);
        this.salida = salida == null ? null : salida.truncatedTo(ChronoUnit.SECONDS);
        validarIntervalo();
        calcularRemuneracion();

    }

    public Jornada(Integer id_jornada, Empleado empleado, LocalDateTime entrada, LocalDateTime salida) {
        if (empleado == null || entrada == null){
            throw new IllegalArgumentException("Empleado ni entrada pueden ser null");
        }

        this.id_jornada = id_jornada;
        this.empleado = empleado;
        this.entrada = entrada.truncatedTo(ChronoUnit.SECONDS);
        this.salida = salida == null ? null : salida.truncatedTo(ChronoUnit.SECONDS);
        validarIntervalo();
        calcularRemuneracion();
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
        if ( entrada == null){
            throw new IllegalArgumentException("entrada no pueden ser null");
        }

        validarIntervalo();
        calcularRemuneracion();
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida == null ? null : salida.truncatedTo(ChronoUnit.SECONDS);
        validarIntervalo();
        calcularRemuneracion();
    }

    public void registrarSalida(){
        this.salida = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        validarIntervalo();
        calcularRemuneracion();
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
        return entrada != null && salida == null;
    }

    private void validarIntervalo(){
        if (salida == null) return;
        if (entrada.isAfter(salida)){
            throw new  IllegalArgumentException("La hora de salida es anterior a la entrada");
        }
    }

    public double getRemuneracionJornada() {
        return remuneracionJornada;
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

    private void calcularRemuneracion(){
        if (salida == null) return;
        if (entrada == null) throw  new IllegalStateException("No existe horario de entrada");
        long horas = ChronoUnit.HOURS.between(entrada, salida);
        long horasExtra = Math.max(horas - DURACION_ESTANDAR, 0);
        long horasEstandar = Math.min(horas, DURACION_ESTANDAR);
        double remuneracionEstandar = horasEstandar * empleado.getRemuneracionHora();
        double remuneracionExtra = horasExtra * empleado.getRemuneracionHoraExtra();


        this.remuneracionJornada = remuneracionEstandar  + remuneracionExtra;
    }
}
