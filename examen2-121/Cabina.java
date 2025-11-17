import java.util.ArrayList;

public class Cabina {
    private int nroCabina;
    private ArrayList<Persona> personasAbordo;

    public Cabina(int nroCabina) {
        this.nroCabina = nroCabina;
        personasAbordo = new ArrayList<>();
    }

    public int getNroCabina() {
        return nroCabina;
    }

    public ArrayList<Persona> getPersonasAbordo() {
        return personasAbordo;
    }

    public void agregarPersona(Persona p) {
        personasAbordo.add(p);
    }

    public float getPesoTotal() {
        float total = 0;
        for (Persona p : personasAbordo)
            total += p.getPesoPersona();
        return total;
    }
}
