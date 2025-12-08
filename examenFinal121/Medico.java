public class Medico {
    private int idMed;  
    private String nombreMed;
    private String apellidoMed;
    private int anioExperiencia;

    public Medico(int idMed, String nombreMed, String apellidoMed, int anioExperiencia) {
        this.idMed = idMed;
        this.nombreMed = nombreMed;
        this.apellidoMed = apellidoMed;
        this.anioExperiencia = anioExperiencia;
    }

    public int getIdMed() {
        return idMed;
    }

    public String getNombreMed() {
        return nombreMed;
    }

    public String getApellidoMed() {
        return apellidoMed;
    }

    public int getAnioExperiencia() {
        return anioExperiencia;
    }

    @Override
    public String toString() {
        return "Medico{idMed=" + idMed + ", nombre='" + nombreMed + " " + apellidoMed + '\'' + ", aniosExp=" + anioExperiencia + '}';
    }
}
