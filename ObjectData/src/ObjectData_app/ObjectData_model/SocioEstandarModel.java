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
        // Se genera una instancia de Datos y se añaden los datos.
        Datos datos = new Datos();
        datos.socioEstandar.add(socioEstandar);

        // Para el ejemplo devolvemos el valor del array de Datos desde aqui:
        // Para imprimir el contenido del array
        for (SocioEstandarModel socio : datos.socioEstandar) {
            System.out.println("Número de socio: " + socio.getNumeroSocio());
            System.out.println("Nombre: " + socio.getNombre());
            // Puedes imprimir más atributos si los tienes en SocioEstandarModel
        }
    }
}