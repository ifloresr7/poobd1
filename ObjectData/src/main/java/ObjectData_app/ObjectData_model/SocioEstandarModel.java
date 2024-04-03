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

    @Override
    public String toString() {
        return "SocioEstandarModel{" +
                "NIF='" + NIF + '\'' +
                ", seguro=" + seguro +
                '}';
    }
    
    // Método para agregar un socio estandar a la lista en Datos.
    public String crearSocioEstandar(SocioEstandarModel socioEstandar) {
        try {
            .socioEstandar.add(socioEstandar);
            return "Se guardo correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }
    // Metodo para buscar por numero de socio y devolver el objeto.
    public static SocioEstandarModel getSocioEstandar(int numeroSocio) {
        for (SocioEstandarModel socioEstandar : .socioEstandar) {
            if(socioEstandar.getNumeroSocio() == numeroSocio){
                return socioEstandar;
            }
        }
        return null;
    }
    // Metodo para actualizar el seguro del socio:
    public String actualizarSeguroSocioEstandar(SeguroModel seguro, SocioEstandarModel socio) {
        try{
            socio.setSeguro(seguro);
            return "Seguro actualizado correctamente.";
        }catch (Exception error) {
            return "No se ha podido guardar los cambios con éxito." + error;
        }
    }
    // Metodo para eliminar por numero de socio.
    public static boolean eliminarSocioEstandar(int numeroSocio) {
        for (SocioEstandarModel socioEstandar : .socioEstandar) {
            if(socioEstandar.getNumeroSocio() == numeroSocio){
                .socioEstandar.remove(socioEstandar);
                return true;
            }
        }
        return false;
    }
    public static double obtenerPrecioSeguro(String nombreSocio) {
        // Buscar el socio por nombre en los arrays correspondientes
        for (SocioEstandarModel socio : .socioEstandar) {
            if (socio.getNombre().equals(nombreSocio)) {
                // Una vez encontrado el socio, obtenemos el tipo de seguro que tiene
                SeguroModel seguro = socio.getSeguro();
                double precioSeguro = seguro.getPrecio();
                return precioSeguro;
            }
        }
        // Devolver un valor predeterminado si no se encuentra el socio
        return 0.0;
    }

    // Método para eliminar socio infantil de la base de datos
    public static boolean eliminarSocioModel(int numSocio) {
        for (SocioEstandarModel socio : .socioEstandar) {
            if (socio.getNumeroSocio() == numSocio) {
                .socioEstandar.remove(socio);
                return true; // Socio eliminado
            }
        }
        return false; // Socio no encontrado
    }
}
