package com.vjhe.sistema_empleados.modelo.persistencia;

import com.vjhe.sistema_empleados.modelo.Empleado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    public void guardar(Empleado empleado){
        String query = "INSERT INTO empleados(nombre, apellido ,r_hora, r_extra) " +
                "VALUES(?,?, ?, ?)";
        try (PreparedStatement statement =  ConexionDB.getConexion().prepareStatement(query)){
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellido());
            statement.setDouble(3, empleado.getRemuneracion());
            statement.setDouble(4, empleado.getRemuneracionExtra());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar nuevo empleado",e);
        }
    }

    public void eliminar(Empleado empleado){
        if (empleado.getId() == null){
            throw new IllegalArgumentException("El empleado no tiene ID");
        }

        String query = "DELETE FROM empleados WHERE id = ?";
        try (PreparedStatement statement =  ConexionDB.getConexion().prepareStatement(query)){
            statement.setInt(1, empleado.getId());
            if (statement.executeUpdate() != 1){
                throw new RuntimeException("El ID de empleado no existe");
            }

        }catch (SQLException e){
            throw new RuntimeException("Error al eliminar el empleado", e);
        }
    }

    public void actualizar(Empleado empleado){
        String query = "UPDATE empleados SET nombre = ?, apellido = ?,  r_hora = ?, r_extra = ? WHERE id = ?";
        if (empleado.getId() == null){
            throw new IllegalArgumentException("El empleado no tiene ID");
        }
        try (PreparedStatement statement =  ConexionDB.getConexion().prepareStatement(query)){

            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellido());
            statement.setDouble(3, empleado.getRemuneracion());
            statement.setDouble(4, empleado.getRemuneracionExtra());
            statement.setInt(5, empleado.getId());

            if (statement.executeUpdate() != 1){
                throw new RuntimeException("El ID de empleado no existe");
            }

        }catch (SQLException e){
            throw new RuntimeException("Error al actualizar el empleado", e);
        }
    }

    public List<Empleado> getTodos(){
        List<Empleado> empleados = new ArrayList<>();

        String query = "SELECT id, nombre, apellido, r_hora, r_extra FROM empleados";
        try (Statement statement = ConexionDB.getConexion().createStatement();
             ResultSet result = statement.executeQuery(query)){

            while (result.next()){
                var empleado = formatearEmpleado(result);
                empleados.add(empleado);
            }
            return empleados;


        } catch (SQLException e){
            throw new RuntimeException("Error al obtener todos los empleados", e);
        }
    }

    private Empleado formatearEmpleado(ResultSet result) throws SQLException {
        var id = result.getInt(1);
        var nombre = result.getString(2);
        var apellido = result.getString(3);
        var remuneracionHora = result.getDouble(4);
        var remuneracionExtra = result.getDouble(5);
        return new Empleado(id,nombre,apellido,remuneracionHora,remuneracionExtra);
    }
}
