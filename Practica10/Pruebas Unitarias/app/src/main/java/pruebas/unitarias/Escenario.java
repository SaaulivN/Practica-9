package pruebas.unitarias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Escenario {
    private String nombre;
    private Elemento[][] campoDeBatalla;

    public Escenario(String nombre) {
        this.nombre = nombre;
        this.campoDeBatalla = new Elemento[10][10];
    }

    public void addElemento(Elemento elemento) {
        int renglon = elemento.getPosicion().getRenglon();
        int columna = elemento.getPosicion().getColumna();
        if (renglon >= 0 && renglon < campoDeBatalla.length && columna >= 0 && columna < campoDeBatalla[0].length) {
            this.campoDeBatalla[renglon][columna] = elemento;
        }
    }

    public void destruirElementos(Posicion p, int radio) {
        ArrayList<Elemento> elementosEnRango = new ArrayList<>();
        int centroRenglon = p.getRenglon();
        int centroColumna = p.getColumna();

        for (int i = centroRenglon - radio; i <= centroRenglon + radio; i++) {
            for (int j = centroColumna - radio; j <= centroColumna + radio; j++) {
                if (i >= 0 && i < campoDeBatalla.length && j >= 0 && j < campoDeBatalla[0].length) {
                    if (campoDeBatalla[i][j] != null) {
                        elementosEnRango.add(campoDeBatalla[i][j]);
                    }
                }
            }
        }

        for (Elemento elemento : elementosEnRango) {
            if (elemento instanceof Destruible) {
                Destruible d = (Destruible) elemento;
                System.out.println(d.destruir());

                Posicion posDestruido = elemento.getPosicion();
                campoDeBatalla[posDestruido.getRenglon()][posDestruido.getColumna()] = null;
            }
        }
    }

    public Elemento getElemento(int renglon, int columna) {
        if (renglon >= 0 && renglon < campoDeBatalla.length && columna >= 0 && columna < campoDeBatalla[0].length) {
            return campoDeBatalla[renglon][columna];
        }
        return null;
    }

    /**
     * Carga la configuración del escenario desde un archivo de texto.
     * @param nombreArchivo El nombre del archivo (ej. "config.txt")
     */
    public void cargarDesdeArchivo(String nombreArchivo) {
        System.out.println("Cargando configuración desde " + nombreArchivo + "...");
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] parts = linea.split(" ");
                if (parts.length < 3) continue;

                String tipo = parts[0];
                Elemento elem = null;
                int renglon, columna;

                try {
                    switch (tipo) {
                        case "Roca":
                            renglon = Integer.parseInt(parts[1]);
                            columna = Integer.parseInt(parts[2]);
                            elem = new Roca(this, new Posicion(renglon, columna));
                            break;
                        case "Bomba":
                            renglon = Integer.parseInt(parts[1]);
                            columna = Integer.parseInt(parts[2]);
                            int radio = Integer.parseInt(parts[3]);
                            elem = new Bomba(this, new Posicion(renglon, columna), radio);
                            break;
                        case "Terricola":
                            String nombreT = parts[1];
                            renglon = Integer.parseInt(parts[2]);
                            columna = Integer.parseInt(parts[3]);
                            elem = new Terricola(nombreT, this, new Posicion(renglon, columna));
                            break;
                        case "Extraterrestre":
                            String nombreE = parts[1];
                            renglon = Integer.parseInt(parts[2]);
                            columna = Integer.parseInt(parts[3]);
                            elem = new Extraterrestre(nombreE, this, new Posicion(renglon, columna));
                            break;
                        default:
                            System.out.println("Tipo de elemento desconocido: " + tipo);
                    }

                    if (elem != null) {
                        this.addElemento(elem);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error de formato numérico en línea: " + linea);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Faltan parámetros en línea: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ADVERTENCIA: No se encontró el archivo de configuración. Se iniciará un escenario vacío.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuración: " + e.getMessage());
        }
    }

    /**
     * Guarda el estado actual del escenario en un archivo de texto.
     * @param nombreArchivo El nombre del archivo (ej. "config.txt")
     */
    public void guardarEnArchivo(String nombreArchivo) {
        System.out.println("Guardando configuración en " + nombreArchivo + "...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < campoDeBatalla.length; i++) {
                for (int j = 0; j < campoDeBatalla[i].length; j++) {
                    Elemento e = campoDeBatalla[i][j];
                    if (e != null) {
                        writer.write(e.toFileString());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de configuración: " + e.getMessage());
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Escenario: ").append(nombre).append("\n");
        sb.append("  0 1 2 3 4 5 6 7 8 9\n");
        for (int i = 0; i < campoDeBatalla.length; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < campoDeBatalla[i].length; j++) {
                Elemento e = campoDeBatalla[i][j];
                char caracter = '-';
                if (e != null) {
                    if (e instanceof Terricola) {
                        caracter = 'T';
                    } else if (e instanceof Extraterrestre) {
                        caracter = 'E';
                    } else if (e instanceof Roca) {
                        caracter = 'R';
                    } else if (e instanceof Bomba) {
                        caracter = 'B';
                    }
                }
                sb.append(caracter).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}