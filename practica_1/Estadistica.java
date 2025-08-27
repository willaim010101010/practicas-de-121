public class Estadistica {
    private float[] datos;

    public Estadistica(float[] datos) {
        this.datos = datos;
    }

    public float promedio() {
        float suma = 0;
        for (float x : datos) {
            suma += x;
        }
        return suma / datos.length;
    }

    public float desviacion() {
        float prom = promedio();
        float suma = 0;
        for (float x : datos) {
            suma += (x - prom) * (x - prom);
        }
        return (float) Math.sqrt(suma / (datos.length - 1));
    }
}
