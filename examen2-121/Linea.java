import java.util.ArrayList;

public class Linea {
    private String color;
    private ArrayList<Persona> filaPersonas;
    private ArrayList<Cabina> cabinas;
    private int cantidadCabinas;

    public Linea(String color) {
        this.color = color;
        filaPersonas = new ArrayList<>();
        cabinas = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Cabina> getCabinas() {
        return cabinas;
    }

    public void agregarCabina(int nroCabina) {
        cabinas.add(new Cabina(nroCabina));
        cantidadCabinas++;
    }

    public void agregarPersona(Persona p) {
        filaPersonas.add(p);
    }

    
    public boolean agregarPrimeraPersonaACabina(Persona p, int nroX) {
        for (Cabina c : cabinas) {
            if (c.getNroCabina() == nroX) {

                if (c.getPersonasAbordo().size() >= 10)
                    return false;

                if (c.getPesoTotal() + p.getPesoPersona() > 850)
                    return false;

                c.agregarPersona(p);
                return true;
            }
        }
        return false;
    }

    
    public float calcularIngreso() {
        float total = 0;

        for (Cabina c : cabinas) {
            for (Persona p : c.getPersonasAbordo()) {

                if (p.getEdad() < 25 || p.getEdad() > 60)
                    total += 1.5;     
                else
                    total += 3;      
            }
        }
        return total;
    }

    
    public float ingresoSoloRegular() {
        float total = 0;

        for (Cabina c : cabinas) {
            for (Persona p : c.getPersonasAbordo()) {

                if (p.getEdad() >= 25 && p.getEdad() <= 60)
                    total += 3;
            }
        }
        return total;
    }
}

