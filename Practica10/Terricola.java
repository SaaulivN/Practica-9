public class Terricola extends Personaje implements Destruible {

    public Terricola(String nombre, Escenario e, Posicion p) {
        super(nombre, e, p);
    }

    @Override
    public String destruir() {
        return "Terricola " + getNombre() + " destruido";
    }

    @Override
    public String toFileString() {
        return "Terricola " + getNombre() + " " + posicion.getRenglon() + " " + posicion.getColumna();
    }
}