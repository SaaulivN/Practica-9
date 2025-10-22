import java.util.Scanner;

public class MisionPosibleMain {
    public static void main(String[] args) {
        String archivoConfig = "config.txt";

        Escenario e = new Escenario("Nostromo");
        e.cargarDesdeArchivo(archivoConfig);

        System.out.println("\n--- ESTADO INICIAL ---");
        System.out.println(e);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el renglón de la bomba a detonar: ");
        int renglon = scanner.nextInt();
        System.out.print("Ingrese la columna de la bomba a detonar: ");
        int columna = scanner.nextInt();

        Elemento elemento = e.getElemento(renglon, columna);

        if (elemento instanceof Bomba) {
            Bomba b = (Bomba) elemento;
            b.explotar();
        } else {
            System.out.println("No se encontró una bomba en la posición (" + renglon + "," + columna + "). No se detonó nada.");
        }

        System.out.println("\n--- ESTADO POST-EXPLOSION ---");
        System.out.println(e);

        e.guardarEnArchivo(archivoConfig);
        
        System.out.println("Programa terminado.");
        scanner.close();
    }
}