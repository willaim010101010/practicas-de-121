import java.util.Locale;
import java.util.Scanner;

public class MainEstadistica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        float[] datos = new float[10];
        System.out.println("Ingrese 10 números separados por espacio:");
        for (int i = 0; i < 10; i++) {
            datos[i] = sc.nextFloat();
        }
        Estadistica est = new Estadistica(datos);
        System.out.printf("El promedio es %.2f\n", est.promedio());
        System.out.printf("La desviación estandard es %.5f\n", est.desviacion());
        sc.close();
    }
}