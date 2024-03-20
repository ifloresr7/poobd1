package ObjectData_app.ObjectData_model;

public class SocioEstandarModel extends SocioModel {
    String NIF;
    SeguroModel seguro;

    // Constructor
    public SocioEstandarModel(int numeroSocio, String nombre, String NIF, SeguroModel seguro) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.seguro = seguro;
    }

    // Getters y Setters
    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public SeguroModel getSeguro() {
        return seguro;
    }

    public void setSeguro(SeguroModel seguro) {
        this.seguro = seguro;
    }

    // Método para agregar un socio estandar a la lista en Datos
    public String crearSocioEstandar(Datos BBDD, SocioEstandarModel socioEstandar) {
        try {
            BBDD.socioEstandar.add(socioEstandar);
            // int num = 1;
            // for (SocioEstandarModel socio : BBDD.socioEstandar) {
            //     System.out.println("\n---- Socio " + num);
            //     System.out.println("- Numero Socio: " + socio.getNumeroSocio());
            //     System.out.println("- Nombre Socio: " + socio.getNombre());
            //     System.out.println("- Seguro: " + socioEstandar.getSeguro());
            //     num++;
            // }
            return "Se guardo correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }
}