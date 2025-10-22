public abstract class Elemento {
    protected Posicion posicion;
    protected Escenario escenario;

    public Elemento(Escenario c, Posicion p) {
        this.escenario = c;
        this.posicion = p;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion p) {
        this.posicion = p;
    }

    /**
     * Devuelve la representación en String del elemento para guardarlo en archivo.
     * @return String formateado para el archivo de configuración.
     */
    public abstract String toFileString();
}