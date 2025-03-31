package com.vjhe.sistema_empleados.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static String USER = "root";
    private static String URL = "jdbc:mariadb://localhost:3306/sistema_empleados";
    private static String PWD = "";
    private static Connection connection;

    public static Connection getConexion(){
        try {
            if (connection == null){
                connection =  DriverManager.getConnection(URL, USER, PWD);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Eror al conectar a la BD", e);
        }
    }
}
