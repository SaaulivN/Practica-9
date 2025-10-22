package pruebas.unitarias;

public abstract class Personaje extends Elemento {
    protected String nombre;
    protected int puntosDeVida;

    public Personaje(String nombre, Escenario e, Posicion p) {
        super(e, p);
        this.nombre = nombre;
        this.puntosDeVida = 100; 
    }

    public String getNombre() {
        return nombre;
    }
}