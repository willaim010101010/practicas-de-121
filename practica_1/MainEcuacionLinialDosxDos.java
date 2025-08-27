
import java.util.Scanner;

public class MainEcuacionLinialDosxDos {
    public static void main(String[] args) {
        Scanner lee = new Scanner(System.in);
        System.out.println("Ingrese los coeficientes de la ecuacion:");
        System.out.print("a: ");
        float a = lee.nextFloat();
        System.out.print("b: ");
        float b = lee.nextFloat();
        System.out.print("c: ");
        float c = lee.nextFloat();
        System.out.print("d: ");
        float d = lee.nextFloat();
        System.out.print("e: ");
        float e = lee.nextFloat();
        System.out.print("f: ");
        float f = lee.nextFloat();

        EcuacionLinialDosxDos ecuacion = new EcuacionLinialDosxDos(a, b, c, d, e, f);

        if (ecuacion.tieneSolucion()) {
            System.out.println("La ecuacion tiene solucion:");
            System.out.println("X: " + ecuacion.getX());
            System.out.println("Y: " + ecuacion.getY());
        } else {
            System.out.println("La ecuacion no tiene solucion.");
        }
        lee.close();
    }
}