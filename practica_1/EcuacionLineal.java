public class EcuacionLineal {
    private float a;
    private float b;
    private float c;

    public EcuacionLineal(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public float getDiscriminante() {
        return this.b * this.b - 4 * this.a * this.c;
    }

    public float getRaiz1() {
        float discriminante = getDiscriminante();
        if (discriminante < 0) {
            throw new ArithmeticException("Raíces complejas");
        }
        return (float)((-this.b + Math.sqrt(discriminante)) / (2 * this.a));
    }

    public float getRaiz2() {
        float discriminante = getDiscriminante();
        if (discriminante < 0) {
            throw new ArithmeticException("Raíces complejas");
        }
        return (float)((-this.b - Math.sqrt(discriminante)) / (2 * this.a));
    }
}