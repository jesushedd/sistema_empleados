package com.vjhe.sistema_empleados.modelo;

import com.vjhe.sistema_empleados.modelo.entidades.Empleado;
import com.vjhe.sistema_empleados.modelo.entidades.Jornada;
import com.vjhe.sistema_empleados.modelo.persistencia.EmpleadoDAO;
import com.vjhe.sistema_empleados.modelo.persistencia.JornadaDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public class SistemaEmpleados {
    private Map<Integer, Empleado> empleadosEnMemoria;
    private List<Jornada> jornadasEnMemoria;
    private Empleado empleadoSeleccionado;
    private JornadaDAO jornadaDAO = new JornadaDAO();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private double remuneracionBase;
    private double remuneracionExtraBase;



    private void sincronizarConDB(){
        empleadoDAO.getTodos().forEach(empleado -> empleadosEnMemoria.put(empleado.getId(), empleado));

        if (empleadoSeleccionado == null) return;
        jornadasEnMemoria = jornadaDAO.listarPorEmpleado(empleadoSeleccionado);
    }


    public void crearEmpleado(String nombre, String apellido, double remuneracionHora, double remuneracionHoraExtra){
        Empleado nuevoEmpleado = new Empleado(nombre, apellido, remuneracionHora, remuneracionHoraExtra);
        empleadoDAO.guardar(nuevoEmpleado);
    }

    public Map<Integer,Empleado> listarEmpleados(){
        sincronizarConDB();
        return empleadosEnMemoria;
    }

    public List<Jornada> listarJornadas(Empleado empleado){
        empleadoSeleccionado = empleado;
        sincronizarConDB();
        return jornadasEnMemoria;
    }

    public void seleccionarEmpleado(Empleado empleado){
        empleadoSeleccionado = empleado;
        sincronizarConDB();
    }

    public void nuevaEntrada(Empleado empleado){
        if (!empleado.equals(empleadoSeleccionado)){
            throw new IllegalStateException("Imposible determinar el estado de la ultima jornada del empleado.");
        }
        if (!jornadasEnMemoria.isEmpty() && jornadasEnMemoria.getFirst().esActiva()){
            throw new IllegalStateException("Existe una jornada activa sin registrar salida del empleado");
        }

        var nueva = Jornada.NUEVA_JORNADA(empleado, LocalDateTime.now());
        jornadaDAO.guardar(nueva);
        sincronizarConDB();
    }

    public void registrarSalida(Empleado empleado, LocalDateTime salida){
        if (!empleado.equals(empleadoSeleccionado)){
            throw new IllegalStateException("Imposible determinar el estado de la ultima jornada del empleado.");
        }

        if (jornadasEnMemoria.isEmpty()){
            throw new IllegalStateException("No existen jornadas del empleado");
        }

        Jornada ultima = jornadasEnMemoria.getFirst();
        if (!ultima.esActiva()){
            throw new IllegalStateException("No existe una jornada activa para el empleado");
        }

        if (salida == null){
            ultima.registrarSalida();
        } else {
            ultima.registrarSalida(salida);
        }



    }

    public void registrarSalida(Empleado empleado){
        registrarSalida(empleado, null);
    }

}
