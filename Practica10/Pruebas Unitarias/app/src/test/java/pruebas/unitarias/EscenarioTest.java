package pruebas.unitarias;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

// Asumiendo que tus clases están en un paquete "modelo"
import modelo.*;

public class EscenarioTest {

    /**
     * Helper para crear un archivo de config temporal
     */
    private void crearArchivoDePrueba(String nombre, String contenido) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombre))) {
            writer.write(contenido);
        }
    }

    @Test
    void testAddElementoYGetElemento() {
        // Arrange
        Escenario e = new Escenario("Test");
        Posicion p = new Posicion(7, 7);
        Roca r = new Roca(e, p);

        // Act
        assertNull(e.getElemento(7, 7), "La posición debe estar vacía inicialmente");
        e.addElemento(r);
        
        // Assert
        assertNotNull(e.getElemento(7, 7), "La posición debe tener un elemento ahora");
        assertEquals(r, e.getElemento(7, 7), "El elemento debe ser la roca que añadimos");
    }

    @Test
    void testCargarDesdeArchivo() throws IOException {
        // Arrange
        String testFile = "test_carga.txt";
        String contenido = "Roca 3 4\nBomba 1 1 2\nTerricola Ripley 5 5";
        crearArchivoDePrueba(testFile, contenido);
        
        Escenario e = new Escenario("Test Carga");

        // Act
        e.cargarDesdeArchivo(testFile);

        // Assert
        assertTrue(e.getElemento(3, 4) instanceof Roca, "Debe haber una Roca en (3,4)");
        assertTrue(e.getElemento(1, 1) instanceof Bomba, "Debe haber una Bomba en (1,1)");
        assertTrue(e.getElemento(5, 5) instanceof Terricola, "Debe haber un Terricola en (5,5)");
        assertEquals("Ripley", ((Terricola)e.getElemento(5, 5)).getNombre());
        assertNull(e.getElemento(9, 9), "Una celda vacía debe ser null");

        // Cleanup
        new File(testFile).delete();
    }

    @Test
    void testDestruirElementos() {
        // Arrange
        Escenario e = new Escenario("Campo de Prueba");
        Bomba b = new Bomba(e, new Posicion(5, 5), 1);
        Terricola t = new Terricola("Ripley", e, new Posicion(5, 6)); // En rango
        Roca r = new Roca(e, new Posicion(4, 5)); // En rango (pero no destruible)
        Extraterrestre ex = new Extraterrestre("Alien", e, new Posicion(1, 1)); // Fuera de rango

        e.addElemento(b);
        e.addElemento(t);
        e.addElemento(r);
        e.addElemento(ex);

        // Act
        e.destruirElementos(new Posicion(5, 5), 1); // Detonación centrada en (5,5)

        // Assert
        assertNull(e.getElemento(5, 5), "La bomba debió ser destruida");
        assertNull(e.getElemento(5, 6), "El Terrícola debió ser destruido");
        assertNotNull(e.getElemento(4, 5), "La Roca NO debe ser destruida (no es Destruible)");
        assertNotNull(e.getElemento(1, 1), "El Extraterrestre está fuera de rango y debe sobrevivir");
    }


    @Test
    void testGuardarEnArchivo() throws IOException {
        // Arrange
        String testFile = "test_guarda.txt";
        Escenario e = new Escenario("Test Guarda");
        
        e.addElemento(new Terricola("Zeke", e, new Posicion(7, 7)));
        e.addElemento(new Extraterrestre("Xeno", e, new Posicion(8, 8)));

        // Act
        e.guardarEnArchivo(testFile);

        // Assert
        // Leemos el archivo para verificar su contenido
        HashSet<String> lineasLeidas = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineasLeidas.add(linea);
            }
        }

        assertEquals(2, lineasLeidas.size(), "El archivo debe contener 2 líneas");
        assertTrue(lineasLeidas.contains("Terricola Zeke 7 7"), "Debe contener al Terricola");
        assertTrue(lineasLeidas.contains("Extraterrestre Xeno 8 8"), "Debe contener al Extraterrestre");

        // Cleanup
        new File(testFile).delete();
    }

    @Test
    void testToString() {
        // Arrange
        Escenario e = new Escenario("Test String");
        e.addElemento(new Roca(e, new Posicion(0, 1)));
        e.addElemento(new Terricola("T", e, new Posicion(1, 2)));

        // Act
        String output = e.toString();
        
        // Assert
        // Hacemos chequeos simples, no probamos la grilla entera
        assertTrue(output.contains("Escenario: Test String"), "Debe mostrar el nombre");
        assertTrue(output.contains("R"), "Debe contener el caracter de Roca");
        assertTrue(output.contains("T"), "Debe contener el caracter de Terricola");
    }
}