import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Consultorio {
    private List<Medico> medicos;
    private List<Consulta> consultas;

    public Consultorio() {
        this.medicos = new ArrayList<>();
        this.consultas = new ArrayList<>();
    }

    public void agregarMedico(Medico m) {
        medicos.add(m);
    }

    public void agregarConsulta(Consulta c) {
        consultas.add(c);
    }

    public boolean eliminarMedicoPorNombre(String nombre, String apellido) {
        Iterator<Medico> it = medicos.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Medico m = it.next();
            if (m.getNombreMed().equalsIgnoreCase(nombre) && m.getApellidoMed().equalsIgnoreCase(apellido)) {
                int id = m.getIdMed();
                it.remove();
                
                consultas.removeIf(c -> c.getIdMed() == id);
                removed = true;
                break;
            }
        }
        return removed;
    }

   
    public void ajustarConsultasFestivas() {
        for (Consulta c : consultas) {
            String mes = c.getMes().toLowerCase();
            if ((c.getDia() == 25 && mes.equals("diciembre")) || (c.getDia() == 1 && mes.equals("enero"))) {
                c.setDia(c.getDia() + 1);
            }
        }
    }

    public List<Consulta> pacientesAtendidosEn(int dia, String mes) {
        List<Consulta> result = new ArrayList<>();
        for (Consulta c : consultas) {
            if (c.getDia() == dia && c.getMes().equalsIgnoreCase(mes)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

   
    public void guardar(String carpeta) throws IOException {
        File dir = new File(carpeta);
        if (!dir.exists()) dir.mkdirs();

        File medFile = new File(dir, "medicos.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(medFile))) {
            for (Medico m : medicos) {
                bw.write(m.getIdMed() + "," + escape(m.getNombreMed()) + "," + escape(m.getApellidoMed()) + "," + m.getAnioExperiencia());
                bw.newLine();
            }
        }

        File conFile = new File(dir, "consultas.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(conFile))) {
            for (Consulta c : consultas) {
                bw.write(c.getCi() + "," + escape(c.getNombrePaciente()) + "," + escape(c.getApellidoPaciente()) + "," + c.getIdMed() + "," + c.getDia() + "," + escape(c.getMes()) + "," + c.getAnio());
                bw.newLine();
            }
        }
    }

    
    public void cargar(String carpeta) throws IOException {
        medicos.clear();
        consultas.clear();

        File dir = new File(carpeta);
        if (!dir.exists() || !dir.isDirectory()) return;

        File medFile = new File(dir, "medicos.csv");
        if (medFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(medFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",", -1);
                    if (parts.length >= 4) {
                        int id = Integer.parseInt(parts[0]);
                        String nombre = unescape(parts[1]);
                        String apellido = unescape(parts[2]);
                        int anios = Integer.parseInt(parts[3]);
                        medicos.add(new Medico(id, nombre, apellido, anios));
                    }
                }
            }
        }

        File conFile = new File(dir, "consultas.csv");
        if (conFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(conFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",", -1);
                    if (parts.length >= 7) {
                        int ci = Integer.parseInt(parts[0]);
                        String nombre = unescape(parts[1]);
                        String apellido = unescape(parts[2]);
                        int idMed = Integer.parseInt(parts[3]);
                        int dia = Integer.parseInt(parts[4]);
                        String mes = unescape(parts[5]);
                        int anio = Integer.parseInt(parts[6]);
                        consultas.add(new Consulta(ci, nombre, apellido, idMed, dia, mes, anio));
                    }
                }
            }
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace(",", "\\,");
    }

    private String unescape(String s) {
        if (s == null) return "";
        return s.replace("\\,", ",").replace("\\\\", "\\");
    }
}
