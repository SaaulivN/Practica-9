package pruebas.unitarias;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Asumiendo que tus clases están en un paquete "modelo"
import modelo.Posicion; 

public class PosicionTest {
    @Test
    void testGetColumna() {
        // Arrange
        Posicion p = new Posicion(5, 10);
        // Act & Assert
        assertEquals(10, p.getColumna(), "La columna debe ser 10");
    }

    @Test
    void testGetRenglon() {
        // Arrange
        Posicion p = new Posicion(5, 10);
        // Act & Assert
        assertEquals(5, p.getRenglon(), "El renglón debe ser 5");
    }
}