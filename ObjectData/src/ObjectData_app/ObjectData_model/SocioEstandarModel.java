package ObjectData_app.ObjectData_model;

public class SocioEstandarModel extends SocioModel {
    private String NIF;
    private SeguroModel seguro;

    // Constructor
    public SocioEstandarModel(int numeroSocio, String nombre, String NIF, SeguroModel seguro) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.seguro = seguro;
    }

    // Método para agregar un socio estandar a la lista en Datos
    public void addSocioEstandar(SocioEstandarModel socioEstandar) {

    }
}