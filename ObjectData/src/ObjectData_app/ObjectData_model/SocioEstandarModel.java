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
    public String crearSocioEstandar(Datos BBDD, SocioEstandarModel socioEstandar) {
        try {
            BBDD.socioEstandar.add(socioEstandar);
            return "Se guardo correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }
    // Metodo para buscar por numero de socio y devolver el objeto.
    public static SocioEstandarModel getSocioEstandar(Datos BBDD, int numeroSocio) {
        for (SocioEstandarModel socioEstandar : BBDD.socioEstandar) {
            if(socioEstandar.getNumeroSocio() == numeroSocio){

                return socioEstandar;
            }
        }
        return null;
    }
    // Metodo para actualizar el seguro del socio:
    public String actualizarSeguroSocioEstandar(Datos BBDD, SeguroModel seguro, SocioEstandarModel socio) {
        try{
            socio.setSeguro(seguro);
            return "Seguro actualizado correctamente.";
        }catch (Exception error) {
            return "No se a podido guardar los cambios con exito." + error;
        }
    }
    // Metodo para eliminar por numero de socio.
    public static boolean eliminarSocioEstandar(Datos BBDD, int numeroSocio) {
        for (SocioEstandarModel socioEstandar : BBDD.socioEstandar) {
            if(socioEstandar.getNumeroSocio() == numeroSocio){
                BBDD.socioEstandar.remove(socioEstandar);
                return true;
            }
        }
        return false;
    }
}

// int num = 1;
// for (SocioEstandarModel socio : BBDD.socioEstandar) {
//     System.out.println("\n---- socio " + num);
//     System.out.println(socio.toString());
//     num++;
// }
