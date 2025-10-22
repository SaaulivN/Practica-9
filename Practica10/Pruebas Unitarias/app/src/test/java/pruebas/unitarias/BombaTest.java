package pruebas.unitarias;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Asumiendo que tus clases están en un paquete "modelo"
import modelo.*;

public class ExtraterrestreTest {
    
    private Escenario e = new Escenario("Test");

    @Test
    void testDestruir() {
        // Arrange
        Extraterrestre ex = new Extraterrestre("Alien", e, new Posicion(2, 2));
        String esperado = "Alien destruido";

        // Act
        String actual = ex.destruir();

        // Assert
        assertEquals(esperado, actual);
    }

    @Test
    void testToFileString() {
        // Arrange
        Extraterrestre ex = new Extraterrestre("Alien", e, new Posicion(2, 2));
        String esperado = "Extraterrestre Alien 2 2";
        
        // Act
        String actual = ex.toFileString();

        // Assert
        assertEquals(esperado, actual);
    }
}