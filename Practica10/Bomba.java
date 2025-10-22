public class Bomba extends Elemento implements Destruible {
    private int rango;

    public Bomba(Escenario e, Posicion p, int rango) {
        super(e, p);
        this.rango = rango;
    }

    public void explotar() {
        System.out.println("Explotando bomba en (" + posicion.getRenglon() + "," + posicion.getColumna() + ")!!");
        escenario.destruirElementos(this.posicion, this.rango);
    }

    @Override
    public String destruir() {
        return "Bomba destruida";
    }

    @Override
    public String toFileString() {
        return "Bomba " + posicion.getRenglon() + " " + posicion.getColumna() + " " + this.rango;
    }
}