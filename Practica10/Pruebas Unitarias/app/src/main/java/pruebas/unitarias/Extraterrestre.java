package pruebas.unitarias;

public class Extraterrestre extends Personaje implements Destruible {

    public Extraterrestre(String nombre, Escenario e, Posicion p) {
        super(nombre, e, p);
    }

    @Override
    public String destruir() {
        return getNombre() + " destruido";
    }

    @Override
    public String toFileString() {
        return "Extraterrestre " + getNombre() + " " + posicion.getRenglon() + " " + posicion.getColumna();
    }
}