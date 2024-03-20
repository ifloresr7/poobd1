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
    
    //Getters y Setters
    public String getNIF() {return NIF;}
    public void setNIF(String NIF) {this.NIF = NIF;}
    public SeguroModel getSeguro() {return seguro;}
    public void setSeguro(SeguroModel seguro) {this.seguro = seguro;}

    // Método para agregar un socio estandar a la lista en Datos
    public void addSocioEstandar(Datos BBDD, SocioEstandarModel socioEstandar) {
        BBDD.socioEstandar.add(socioEstandar);
    }
}