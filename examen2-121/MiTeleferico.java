import java.util.ArrayList;

public class MiTeleferico {
    private ArrayList<Linea> lineas;

    public MiTeleferico() {
        lineas = new ArrayList<>();
    }

    public void agregarLinea(String color) {
        lineas.add(new Linea(color));
    }

    public Linea getLinea(String color) {
        for (Linea l : lineas)
            if (l.getColor().equalsIgnoreCase(color))
                return l;
        return null;
    }

    
    public boolean verificarReglas() {
        for (Linea l : lineas) {
            for (Cabina c : l.getCabinas()) {

                if (c.getPersonasAbordo().size() > 10)
                    return false;

                if (c.getPesoTotal() > 850)
                    return false;
            }
        }
        return true;
    }

    
    public float ingresoTotal() {
        float total = 0;
        for (Linea l : lineas)
            total += l.calcularIngreso();
        return total;
    }

    
    public Linea lineaConMasIngresoRegular() {
        Linea mejor = null;
        float max = -1;

        for (Linea l : lineas) {
            float ir = l.ingresoSoloRegular();
            if (ir > max) {
                max = ir;
                mejor = l;
            }
        }
        return mejor;
    }
}

