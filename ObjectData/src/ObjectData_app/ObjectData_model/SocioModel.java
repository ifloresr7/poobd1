package ObjectData_app.ObjectData_model;

public abstract class SocioModel {
    private int numeroSocio;
    private String nombre;

    // Constructor
    public SocioModel(int numeroSocio, String nombre) {
        this.numeroSocio = numeroSocio;
        this.nombre = nombre;
    }

    // Getters
    public int getNumeroSocio() {
        return numeroSocio;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString
    @Override
    public String toString() {
        return "Socio{" +
                "numeroSocio=" + numeroSocio +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    // Método para comprobar si un socio existe mediante el numeroSocio
    public static boolean comprobarSocioByCodigo(Datos BBDD, int codigoSocio) {
        // Comprobar en la lista de socios estándar
        for (SocioEstandarModel socio : BBDD.socioEstandar) {
            if (socio.getNumeroSocio() == codigoSocio) {
                return true;
            }
        }
        // Comprobar en la lista de socios federados
        for (SocioFederadoModel socio : BBDD.socioFederado) {
            if (socio.getNumeroSocio() == codigoSocio) {
                return true;
            }
        }
        // Comprobar en la lista de socios infantiles
        for (SocioInfantilModel socio : BBDD.socioInfantil) {
            if (socio.getNumeroSocio() == codigoSocio) {
                return true;
            }
        }
        // Si no se encuentra en ninguna lista, devolver false
        return false;
    }

    public static boolean buscarSocioNombre(Datos BBDD, String nombre) {

        // Comprobar en la lista de socios estándar
        for (SocioEstandarModel socio : BBDD.socioEstandar) {
            if (socio.getNombre().equals(nombre)) {
                return true;
            }
        }
        // Comprobar en la lista de socios federados
        for (SocioFederadoModel socio : BBDD.socioFederado) {
            if (socio.getNombre().equals(nombre)) {
                return true;
            }
        }
        // Comprobar en la lista de socios infantiles
        for (SocioInfantilModel socio : BBDD.socioInfantil) {
            if (socio.getNombre().equals(nombre)) {
                return true;
            }
        }
        // Si no se encuentra en ninguna lista, devolver false
        return false;
    }

    public static String[] listarSociosModel(Datos BBDD) {
        // Comprobar en la lista de socios estándar
        String listado = "  - Sin datos.";
        int contador = 1;
        for (SocioEstandarModel socio : BBDD.socioEstandar) {
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Tipo de socio: Estandar";
            contador++;
        }
        // Comprobar en la lista de socios federados
        for (SocioFederadoModel socio : BBDD.socioFederado) {
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Tipo de socio: Federado";
            contador++;
        }
        // Añadir usuarios a la lista 
        for (SocioInfantilModel socio : BBDD.socioInfantil) {
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Tipo de socio: Infantil";
            contador++;
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    public static String[] listarSociosEstandarModel(Datos BBDD) {
        String listado = "  - Sin datos.";
        int contador = 1;
        for (SocioEstandarModel socio : BBDD.socioEstandar) {
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | NIF: " + socio.getNIF() + " | Seguro: " + socio.seguro.getTipo();
            contador++;
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    public static String[] listarSociosFederadosModel(Datos BBDD) {
        String listado = "  - Sin datos.";
        int contador = 1;
        for (SocioFederadoModel socio : BBDD.socioFederado) {
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | NIF: " + socio.getNIF() + " | Federación: "
                    + socio.federacion.getNombre();
            contador++;
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    public static String[] listarSociosInfantilesModel(Datos BBDD) {
        String listado = "  - Sin datos.";
        int contador = 1;
        for (SocioInfantilModel socio : BBDD.socioInfantil) {
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Numero socio parental: " + socio.getNumeroSocioPadreMadre();
            contador++;
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    public static boolean eliminarSocioModel(Datos BBDD, String numeroSocio) {
        // Buscar el socio en la lista de socios
        for (SocioModel socio : BBDD.socios) {
            if (socio.getNumeroSocio().equals(numeroSocio)) {
                BBDD.socios.remove(socio);
                return true; // Socio eliminado exitosamente
            }
        }
        return false; // No se encontró el socio con el número dado
    }
}
