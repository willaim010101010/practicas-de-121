import java.util.Scanner;

public class MainEcuacionLineal {
    public static void main(String[] args) {
        Scanner lee = new Scanner(System.in);
        System.out.println("Ingrese los coeficientes de la ecuacion:");
        System.out.print("a: ");
        float a = lee.nextFloat();
        System.out.print("b: ");
        float b = lee.nextFloat();
        System.out.print("c: ");
        float c = lee.nextFloat();

        EcuacionLineal ecuacion = new EcuacionLineal(a, b, c);

        float discriminante = ecuacion.getDiscriminante();
        if (discriminante < 0) {
            System.out.println("La ecuacion tiene raices complejas.");
        } else if (discriminante == 0) {
            System.out.println("La ecuacion tiene una raiz doble:");
            System.out.println("Raiz: " + ecuacion.getRaiz1());
        } else {
            System.out.println("La ecuacion tiene dos raices reales:");
            System.out.println("Raiz 1: " + ecuacion.getRaiz1());
            System.out.println("Raiz 2: " + ecuacion.getRaiz2());
        }
        lee.close();
    }
}