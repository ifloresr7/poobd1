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
    public static boolean comprobarSocioPorNumSocio(Datos BBDD, int codigoSocio) {
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

    public static String[] listarSociosModel(Datos BBDD) {
        // Comprobar en la lista de socios estándar
        String listado = "";
        int contador = 0;
        for (SocioEstandarModel socio : BBDD.socioEstandar) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Tipo de socio: Estandar";
        }
        // Comprobar en la lista de socios federados
        for (SocioFederadoModel socio : BBDD.socioFederado) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Tipo de socio: Federado";
        }
        // Añadir usuarios a la lista
        for (SocioInfantilModel socio : BBDD.socioInfantil) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Tipo de socio: Infantil";

        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    public static String[] listarSociosEstandarModel(Datos BBDD) {
        String listado = "";
        int contador = 0;
        for (SocioEstandarModel socio : BBDD.socioEstandar) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | NIF: " + socio.getNIF() + " | Seguro: " + socio.seguro.getTipo();
        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    public static String obtenerNombreSocio(Datos BBDD, int numeroSocio) {
        String nombre = null;
        for (SocioInfantilModel socio : BBDD.socioInfantil) {
            if (socio.getNumeroSocio() == numeroSocio) {
                nombre = socio.getNombre();
            }
        }
        for (SocioFederadoModel socio : BBDD.socioFederado) {
            if (socio.getNumeroSocio() == numeroSocio) {
                nombre = socio.getNombre();
            }
        }
        for (SocioEstandarModel socio : BBDD.socioEstandar) {
            if (socio.getNumeroSocio() == numeroSocio) {
                nombre = socio.getNombre();
            }
        }
        return nombre;
    }

    public static String[] listarSociosFederadosModel(Datos BBDD) {
        String listado = "";
        int contador = 0;
        for (SocioFederadoModel socio : BBDD.socioFederado) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | NIF: " + socio.getNIF() + " | Federación: "
                    + socio.federacion.getNombre();
        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    public static String[] listarSociosInfantilesModel(Datos BBDD) {
        String listado = "";
        int contador = 0;
        for (SocioInfantilModel socio : BBDD.socioInfantil) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Numero socio parental: " + socio.getNumeroSocioPadreMadre();
        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    // Método para obtener el tipo de socio de un socio si existe mediante el
    // numeroSocio
    public static String obtenerTipoSocioPorNumSocio(Datos BBDD, int codigoSocio) {
        // Comprobar en la lista de socios estándar
        for (SocioEstandarModel socio : BBDD.socioEstandar) {
            if (socio.getNumeroSocio() == codigoSocio) {
                return "Estandar";
            }
        }
        // Comprobar en la lista de socios federados
        for (SocioFederadoModel socio : BBDD.socioFederado) {
            if (socio.getNumeroSocio() == codigoSocio) {
                return "Federado";
            }
        }
        // Comprobar en la lista de socios infantiles
        for (SocioInfantilModel socio : BBDD.socioInfantil) {
            if (socio.getNumeroSocio() == codigoSocio) {
                return "Infantil";
            }
        }
        // Si no se encuentra en ninguna lista, devolver false
        return null;
    }
}