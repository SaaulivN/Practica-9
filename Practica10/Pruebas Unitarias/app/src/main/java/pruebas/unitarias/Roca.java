package pruebas.unitarias;

public class Roca extends Elemento {
    public Roca(Escenario e, Posicion p) {
        super(e, p);
    }

    @Override
    public String toFileString() {
        return "Roca " + posicion.getRenglon() + " " + posicion.getColumna();
    }
}