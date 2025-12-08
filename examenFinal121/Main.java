import java.util.List;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            
            Consultorio consultorio = new Consultorio();

            Medico m1 = new Medico(1, "Juan", "Perez", 10);
            Medico m2 = new Medico(2, "Ana", "Gomez", 5);
            Medico m3 = new Medico(3, "Luis", "Martinez", 8);
            consultorio.agregarMedico(m1);
            consultorio.agregarMedico(m2);
            consultorio.agregarMedico(m3);

            consultorio.agregarConsulta(new Consulta(101, "Carlos", "Lopez", 1, 10, "abril", 2025));
            consultorio.agregarConsulta(new Consulta(102, "Maria", "Diaz", 1, 25, "diciembre", 2025)); // Navidad
            consultorio.agregarConsulta(new Consulta(103, "Pedro", "Suarez", 1, 1, "enero", 2026)); // Año Nuevo

            consultorio.agregarConsulta(new Consulta(201, "Lucia", "Fernandez", 2, 5, "junio", 2025));
            consultorio.agregarConsulta(new Consulta(202, "Diego", "Rios", 2, 8, "diciembre", 2025)); // ejemplo cumpleaños
            consultorio.agregarConsulta(new Consulta(203, "Sofia", "Castro", 2, 15, "julio", 2025));

            consultorio.agregarConsulta(new Consulta(301, "Martina", "Ortega", 3, 20, "marzo", 2025));
            consultorio.agregarConsulta(new Consulta(302, "Andres", "Vega", 3, 25, "diciembre", 2025)); // Navidad
            consultorio.agregarConsulta(new Consulta(303, "Elena", "Molina", 3, 1, "enero", 2026)); // Año Nuevo

            System.out.println("== Datos iniciales en memoria ==");
            System.out.println("Médicos:");
            for (Medico m : consultorio.getMedicos()) System.out.println(m);
            System.out.println("Consultas:");
            for (Consulta c : consultorio.getConsultas()) System.out.println(c);

        
            String carpeta = "data";
            consultorio.guardar(carpeta);
            System.out.println("\nDatos guardados en carpeta '" + carpeta + "'\n");

            
            Consultorio cargado = new Consultorio();
            cargado.cargar(carpeta);
            System.out.println("== Datos recargados desde disco ==");
            System.out.println("Médicos:");
            for (Medico m : cargado.getMedicos()) System.out.println(m);
            System.out.println("Consultas:");
            for (Consulta c : cargado.getConsultas()) System.out.println(c);

            
            System.out.println("\nEliminando al médico Juan Perez y sus consultas en datos recargados...");
            boolean eliminado = cargado.eliminarMedicoPorNombre("Juan", "Perez");
            System.out.println("Médico eliminado: " + eliminado);
            System.out.println("Consultas después de eliminar médico:");
            for (Consulta c : cargado.getConsultas()) System.out.println(c);

            
            System.out.println("\nAjustando consultas festivas (Navidad y Año Nuevo) en datos recargados...");
            cargado.ajustarConsultasFestivas();
            for (Consulta c : cargado.getConsultas()) System.out.println(c);

            
            int diaCumple = 8;
            String mesCumple = "diciembre";
            System.out.println("\nPacientes atendidos el día de tu cumpleaños (" + diaCumple + " " + mesCumple + "):");
            List<Consulta> atendidos = cargado.pacientesAtendidosEn(diaCumple, mesCumple);
            if (atendidos.isEmpty()) {
                System.out.println("No hay pacientes en esa fecha.");
            } else {
                for (Consulta c : atendidos) System.out.println(c);
            }

    
            cargado.guardar(carpeta);
            System.out.println("\nCambios guardados de nuevo en '" + carpeta + "'.");

        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
