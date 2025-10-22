package pruebas.unitarias;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Asumiendo que tus clases están en un paquete "modelo"
import modelo.*;

public class TerricolaTest {
    
    private Escenario e = new Escenario("Test");
    
    @Test
    void testDestruir() {
        // Arrange
        Terricola t = new Terricola("Ripley", e, new Posicion(1, 1));
        String esperado = "Terricola Ripley destruido";

        // Act
        String actual = t.destruir();

        // Assert
        assertEquals(esperado, actual);
    }

    @Test
    void testToFileString() {
        // Arrange
        Terricola t = new Terricola("Ripley", e, new Posicion(1, 1));
        String esperado = "Terricola Ripley 1 1";
        
        // Act
        String actual = t.toFileString();

        // Assert
        assertEquals(esperado, actual);
    }
}