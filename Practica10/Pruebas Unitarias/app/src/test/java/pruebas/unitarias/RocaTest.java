package pruebas.unitarias;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Asumiendo que tus clases están en un paquete "modelo"
import modelo.*;

public class RocaTest {
    @Test
    void testToFileString() {
        // Arrange
        Escenario e = new Escenario("Test");
        Posicion p = new Posicion(3, 4);
        Roca r = new Roca(e, p);
        
        String esperado = "Roca 3 4";

        // Act
        String actual = r.toFileString();

        // Assert
        assertEquals(esperado, actual);
    }
}