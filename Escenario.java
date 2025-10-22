package practica09;
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < campoDeBatalla.length; i++) {
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
