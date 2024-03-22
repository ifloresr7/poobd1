package ObjectData_app.ObjectData_model;

public class FederacionModel {
    String codigo;
    String nombre;

    // Constructor
    public FederacionModel(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString
    @Override
    public String toString() {
        return "Codigo: " + codigo + " | Nombre: " + nombre;
    }

    //Metodos porpios
    public static String[] obtenerListadoFederacion(Datos BBDD){
        String listado = "";
        int contador = 1;
        for (FederacionModel federacion : BBDD.federacion) {
            listado += "\n    - " + contador + ". " + federacion.toString();
            contador++;
        }
        return new String[] {listado, String.valueOf(contador)};
    }

    public static FederacionModel obtenerFederacion(Datos BBDD, int seleccion){
        int contador = 1;
        for (FederacionModel federacion : BBDD.federacion) {
            if(contador == seleccion){
                return federacion;
            }
            contador++;
        }
        return null;
    }
}