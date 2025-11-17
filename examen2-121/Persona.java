public class Persona {
    private String nombre;
    private int edad;
    private float pesoPersona;

    public Persona(String nombre, int edad, float peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.pesoPersona = peso;
    }

    public int getEdad() {
        return edad;
    }

    public float getPesoPersona() {
        return pesoPersona;
    }

    public String getNombre() {
        return nombre;
    }
}

