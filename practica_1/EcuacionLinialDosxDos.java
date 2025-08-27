

public class EcuacionLinialDosxDos {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;

    public EcuacionLinialDosxDos(float a, float b, float c, float d, float e, float f){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public boolean tieneSolucion(){
        return (this.a * this.d - this.b * this.c) != 0;
    }

    public float getX(){
        if (this.tieneSolucion()) {
            return (this.e * this.d - this.b * this.f) / (this.a * this.d - this.b * this.c);
        }
        return 0;
    }

    public float getY(){
        if (this.tieneSolucion()) {
            return (this.a * this.f - this.e * this.c) / (this.a * this.d - this.b * this.c);
        }
        return 0;
    }
}