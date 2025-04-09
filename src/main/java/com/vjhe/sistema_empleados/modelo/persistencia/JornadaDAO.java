package com.vjhe.sistema_empleados.modelo.persistencia;

import com.vjhe.sistema_empleados.modelo.entidades.Empleado;
import com.vjhe.sistema_empleados.modelo.entidades.Jornada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JornadaDAO {
    public void guardar(Jornada jornada){
        String query = "INSERT INTO jornadas(empleado_id, entrada ,salida) " +
                "VALUES(?,?, ?)";
        try (PreparedStatement statement =  ConexionDB.getConexion().prepareStatement(query)){
            statement.setInt(1,jornada.getEmpleadoID() );
            statement.setObject(2, jornada.getEntrada());
            statement.setObject(3, jornada.getSalida());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la nueva jornada",e);
        }
    }

    public List<Jornada> listarPorEmpleado(Empleado empleado){
        String query = "SELECT id, entrada, salida FROM  jornadas WHERE empleado_id = ? ORDER BY entrada DESC" ;

        List<Jornada> jornadasEmpleado = new ArrayList<>();

        try (PreparedStatement statement = ConexionDB.getConexion().prepareStatement(query)) {
            statement.setInt(1, empleado.getId());
            try (ResultSet result = statement.executeQuery()){
                while (result.next()){
                    var jornada = parseJornada(result, empleado);
                    jornadasEmpleado.add(jornada);
                }
            }
            return jornadasEmpleado;
        }catch (SQLException e){
            throw new RuntimeException("Error al obtener las jornadas del empleado con id: " + empleado.getId());
        }
    }

    private Jornada parseJornada(ResultSet resultSet, Empleado empleado) throws SQLException {
        int id = resultSet.getInt("id");
        LocalDateTime entrada = resultSet.getObject("entrada", LocalDateTime.class);
        LocalDateTime salida = resultSet.getObject("salida", LocalDateTime.class);
        return new Jornada(id,empleado, entrada,salida);
    }

}
