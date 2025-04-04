package com.vjhe.sistema_empleados.modelo;

import com.vjhe.sistema_empleados.modelo.persistencia.ConexionDB;
import com.vjhe.sistema_empleados.modelo.persistencia.EmpleadoDAO;
import com.vjhe.sistema_empleados.modelo.persistencia.JornadaDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTest {
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private JornadaDAO jornadaDAO = new JornadaDAO();

    @Test
    public  void testGuardarEmpleados(){
        reiniciarDB();
        //instanciar lista de  empleados
        List<Empleado> empleadosExpected = crearListaEmpleados();

        //guardar empleados en la base de datos
        empleadosExpected.forEach(empleado -> empleadoDAO.guardar(empleado));


        //obtener los empleaos de la base de datos
        List<Empleado> empleadosDB =  empleadoDAO.getTodos();
        Assertions.assertIterableEquals(empleadosExpected, empleadosDB, "Los empleados non son iguales!");
    }
    @Test
    public void  testGuardarJornadas(){
        var empleado6 = Empleado.EMPLEADO_BASE("nombre6", "apellido6");
        empleado6.setId(6);
        empleadoDAO.guardar(empleado6);
        List<Jornada> jornadasExpected = new ArrayList<>();
        for (int i = 0, id = 1; i < 5; i++, id++) {
            Jornada jornada1 = Jornada.NUEVA_JORNADA(empleado6, LocalDateTime.now());
            jornadasExpected.add(jornada1);
            jornadaDAO.guardar(jornada1);
            jornada1.setId_jornada(id);
        }

        List<Jornada> jornadasResult = jornadaDAO.listarPorEmpleado(empleado6);

        Assertions.assertIterableEquals(jornadasExpected, jornadasResult, "No son igales, las jornadas del empleado id 6");







    }

    private List<Empleado> crearListaEmpleados(){
        //instanciar lista de  empleados
        List<Empleado> empleados = List.of( Empleado.EMPLEADO_BASE("nombre1", "apellido1"),
                Empleado.EMPLEADO_BASE("nombre2", "apellido2"),
                Empleado.EMPLEADO_BASE("nombre3", "apellido3"),
                Empleado.EMPLEADO_BASE("nombre4", "apellido4"),
                Empleado.EMPLEADO_BASE("nombre5", "apellido5"));

        //asignar id de 1 a 5 a los empleados instanciados previamente
        for (int i = 0, id = i + 1; i < empleados.size() ; i++, id++) {
            empleados.get(i).setId(id);
        }
        return empleados;
    }



    private void reiniciarDB(){
        eliminarTablas();
        crearTablas();
    }

    private void eliminarTablas(){
        String query = "DROP TABLE jornadas";
        try (Statement statement = ConexionDB.getConexion().createStatement()){
            statement.execute(query);
        }catch (SQLException e){
            throw new RuntimeException("No se pudo eliminar la tabla jornadas");
        }

        query = "DROP TABLE empleados";
        try (Statement statement = ConexionDB.getConexion().createStatement()){
            statement.execute(query);
        }catch (SQLException e){
            throw new RuntimeException("No se pudo eliminar la tabla empleados");
        }
    }

    private void crearTablas(){
        String esquema = """
                CREATE TABLE empleados(
                    id  INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                    nombre VARCHAR(100) NOT NULL,
                    apellido VARCHAR(100) NOT NULL,
                    r_hora DECIMAL(6,2) NOT NULL,
                    r_extra DECIMAL(6,2) NOT NULL
                )""";
        try (Statement statement = ConexionDB.getConexion().createStatement()){
            statement.execute(esquema);
        }catch (SQLException e){
            throw new RuntimeException("No se crear la tabla empleados");
        }


        esquema = """
                CREATE TABLE jornadas(
                	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                	empleado_id INT NOT NULL,
                	entrada DATETIME NOT NULL,
                	salida DATETIME,
                	FOREIGN KEY (empleado_id) REFERENCES empleados(id)
                )
                """;
        try (Statement statement = ConexionDB.getConexion().createStatement()){
            statement.execute(esquema);
        }catch (SQLException e){
            throw new RuntimeException("No se crear la tabla jornadas");
        }
    }
}
