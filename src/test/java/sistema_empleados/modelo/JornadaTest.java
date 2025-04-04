package sistema_empleados.modelo;

import com.vjhe.sistema_empleados.modelo.Empleado;
import com.vjhe.sistema_empleados.modelo.Jornada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class JornadaTest {
    Empleado empleadoTest = Empleado.EMPLEADO_BASE("nombreTest", "ApellidoTest");

    @Test
    public void testCalculoRemuneraciones(){
        LocalDateTime entrada = LocalDateTime.of(2025, Month.APRIL, 4, 0,0,0);
        Jornada jornada = Jornada.NUEVA_JORNADA(empleadoTest,entrada );
        //Test Jornada sin salida registrada
        Assertions.assertEquals(0,jornada.getRemuneracionJornada());

        //Test Jornada de 8 horas exactas
        LocalDateTime salida = LocalDateTime.of(2025, Month.APRIL, 4, 8, 0, 0);
        jornada.setSalida(salida);
        Assertions.assertEquals(empleadoTest.getRemuneracionHora() * 8, jornada.getRemuneracionJornada() );
        //Test Jornada de 8 horas y media -> 8 horas normales  + 1 hora extra
        salida = LocalDateTime.of(2025, Month.APRIL, 4, 8,30,0);
        jornada.setSalida(salida);
        Assertions.assertEquals(empleadoTest.getRemuneracionHora() * Jornada.DURACION_ESTANDAR +
                                empleadoTest.getRemuneracionHoraExtra(), jornada.getRemuneracionJornada() );

        //Test Jornada de 10 horas a -> 8 horas normales  + 2 hora extra
        salida = LocalDateTime.of(2025, Month.APRIL, 4, 10,20,0);
        jornada.setSalida(salida);
        Assertions.assertEquals(empleadoTest.getRemuneracionHora() * Jornada.DURACION_ESTANDAR +
                empleadoTest.getRemuneracionHoraExtra() * 2, jornada.getRemuneracionJornada() );

    }
}
