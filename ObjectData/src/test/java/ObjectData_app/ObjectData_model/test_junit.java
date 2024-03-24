package ObjectData_app.ObjectData_model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class test_junit {
    private Datos datosPrueba;
    private ExcursionModel excursionPrueba;

    @BeforeEach
    void setUp() {
        datosPrueba = new Datos(); 
        excursionPrueba = new ExcursionModel(1, "Excursion prueba", new Date(), 3, 150.0);
        datosPrueba.excursion.add(excursionPrueba);
    }

    @Test
    void testCrearExcursionModel() {
        ExcursionModel nuevaExcursion = new ExcursionModel(2, "Nueva Excursion", new Date(), 5, 200.0);
        String resultado = nuevaExcursion.crearExcursionModel(datosPrueba, nuevaExcursion);
        assertEquals("¡Se ha guardado correctamente!", resultado, "La excursion se ha guardado en Datos");
        assertTrue(datosPrueba.excursion.contains(nuevaExcursion), "La nueva excursion debe estar en la lista de excursiones de Datos");
    }

    @Test
    void testObtenerListadoExcursiones() {
        String[] resultado = ExcursionModel.obtenerListadoExcursiones(datosPrueba);
        assertNotNull(resultado, "El resultado no puede ser null");
        assertTrue(resultado.length == 2, "El array resultante debe tener dos elementos");
        assertTrue(Integer.parseInt(resultado[1]) >= 1, "Debe haber al menos una excursion en los datos de prueba");
    }
}