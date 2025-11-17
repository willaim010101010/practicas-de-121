public class Main {
    public static void main(String[] args) {

        MiTeleferico mt = new MiTeleferico();

        mt.agregarLinea("Rojo");
        mt.agregarLinea("Amarillo");
        mt.agregarLinea("Verde");

        Linea rojo = mt.getLinea("Rojo");
        rojo.agregarCabina(1);
        rojo.agregarCabina(2);

        Persona p1 = new Persona("Luis", 30, 70);
        Persona p2 = new Persona("Ana", 20, 60);
        Persona p3 = new Persona("Carlos", 65, 80);

        
        rojo.agregarPrimeraPersonaACabina(p1, 1);
        rojo.agregarPrimeraPersonaACabina(p2, 1);
        rojo.agregarPrimeraPersonaACabina(p3, 2);

        
        System.out.println("Reglas correctas? " + mt.verificarReglas());

        
        System.out.println("Ingreso total: " + mt.ingresoTotal());


        Linea mejor = mt.lineaConMasIngresoRegular();
        System.out.println("Línea con más ingreso regular: " + mejor.getColor());
    }
}
