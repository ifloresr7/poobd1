package ObjectData_app.ObjectData_model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InscripcionModel{
    private int numeroInscripcion;
    private int numeroSocio;
    private int numeroExcursion;
    private Date fechaInscripcion;

    // Constructor
    public InscripcionModel(int numeroInscripcion,int numeroSocio,int numeroExcursion,Date fechaInscripcion){
        this.numeroInscripcion = numeroInscripcion;
        this.numeroSocio = numeroSocio;
        this.numeroExcursion = numeroExcursion;
        this.fechaInscripcion = fechaInscripcion;
    }
    public static String[] listarInscripcionesFecha(Datos BBDD, String FechaI, String FechaF) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato de fecha

        // Parsear las fechas de inicio y fin
        Date fechaInicio, fechaFin;
        try {
            fechaInicio = sdf.parse(FechaI);
            fechaFin = sdf.parse(FechaF);
        } catch (ParseException e) {
            // Mensaje de error si hay un problema con el formato de fecha
            System.out.println("Error al parsear las fechas: " + e.getMessage());
            return new String[]{"Error en el formato de fecha.", "0"};
        }

        String listado = "";
        int contador = 0;

        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            Date fechaInscripcion = inscripcion.fechaInscripcion;

            // Mensaje de depuración para verificar las fechas de inscripción
            System.out.println("Fecha de inscripción: " + fechaInscripcion);
            if (fechaInscripcion.after(fechaInicio) && fechaInscripcion.before(fechaFin)) {
                String nombreExcursion = ExcursionModel.obtenerNombreExcursionPorId(BBDD, inscripcion.getNumeroExcursion());
                String tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD,inscripcion.getNumeroSocio());
                String nombreSocio = SocioModel.obtenerNombreSocio(BBDD, inscripcion.getNumeroSocio());
                double precio = ExcursionModel.obtenerPrecioExcursion(BBDD, inscripcion.getNumeroExcursion());
                if (tipoSocio.equals("Federado")) {
                    precio = precio - (precio * 0.1);
                }
                if (tipoSocio.equals("Estandar")) {
                    double precioSeguro = SocioEstandarModel.obtenerPrecioSeguro(BBDD, nombreSocio);
                    precio = precio + precioSeguro;
                }
                contador++;
                listado += "\n    - " + contador + ".Nombre del socio: " + nombreSocio + " | Identificador de inscripción: " + inscripcion.numeroInscripcion + " | Excursión: "
                        + nombreExcursion + " | Fecha de inscripcion: " + inscripcion.fechaInscripcion + " | Precio de la inscripcion: " + precio;
            }
            if (contador == 0) {
                listado = "\n  - Sin datos.";
            }
        }
            return new String[]{listado, String.valueOf(contador)};
        }

    public static String[] listarInscripciones(Datos BBDD)
    {

        String listado = "";
        int contador = 0;

        for (InscripcionModel inscripcion : BBDD.inscripcion) {

                String nombreExcursion = ExcursionModel.obtenerNombreExcursionPorId(BBDD, inscripcion.getNumeroExcursion());
                String tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD, inscripcion.getNumeroSocio());
                String nombreSocio = SocioModel.obtenerNombreSocio(BBDD, inscripcion.getNumeroSocio());
                double precio = ExcursionModel.obtenerPrecioExcursion(BBDD, inscripcion.getNumeroExcursion());

                if (tipoSocio.equals("Federado")) {
                    precio -= (precio * 0.1);
                }
                if (tipoSocio.equals("Estandar")) {
                    double precioSeguro = SocioEstandarModel.obtenerPrecioSeguro(BBDD, nombreSocio);
                    precio += precioSeguro;
                }
                contador++;
                listado += "\n    - " + contador + ". Nombre del socio: " + nombreSocio + " | Identificador de inscripción: " + inscripcion.getNumeroInscripcion() + " | Excursión: "
                        + nombreExcursion + " | Fecha de inscripción: " + inscripcion.getFechaInscripcion() + " | Precio de la inscripción: " + precio;
            }

            if (contador == 0)
            {
                listado = "\n  - Sin datos.";
            }
            return new String[] { listado, String.valueOf(contador) };
        }

    public static String[] listarInscripciones(Datos BBDD, int numeroSocio) {
        String listado = "";
        int contador = 0;

        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            if (inscripcion.getNumeroSocio() == numeroSocio) {
                String nombreExcursion = ExcursionModel.obtenerNombreExcursionPorId(BBDD, inscripcion.getNumeroExcursion());
                String tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD, inscripcion.getNumeroSocio());
                String nombreSocio = SocioModel.obtenerNombreSocio(BBDD, inscripcion.getNumeroSocio());
                double precio = ExcursionModel.obtenerPrecioExcursion(BBDD, inscripcion.getNumeroExcursion());

                if (tipoSocio.equals("Federado")) {
                    precio -= (precio * 0.1);
                }
                if (tipoSocio.equals("Estandar")) {
                    double precioSeguro = SocioEstandarModel.obtenerPrecioSeguro(BBDD, nombreSocio);
                    precio += precioSeguro;
                }
                contador++;
                listado += "\n    - " + contador + ". Nombre del socio: " + nombreSocio + " | Identificador de inscripción: " + inscripcion.getNumeroInscripcion() + " | Excursión: "
                        + nombreExcursion + " | Fecha de inscripción: " + inscripcion.getFechaInscripcion() + " | Precio de la inscripción: " + precio;
            }
        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }


    public static boolean eliminarInscripcionNumero(Datos bbdd, int num) {
        for (int i = 0; i < bbdd.inscripcion.size(); i++) {
            InscripcionModel inscripcion = bbdd.inscripcion.get(i);
            if (inscripcion.getNumeroInscripcion() == num) {
                // Obtener el número de excursión de la inscripción
                int numExcursion = inscripcion.getNumeroExcursion();

                // Buscar la fecha de la excursión correspondiente en el array de excursiones
                for (ExcursionModel excursion : bbdd.excursion) {
                    if (excursion.getNumeroExcursion() == numExcursion) {
                        // Comparar la fecha de inscripción con la fecha de la excursión
                        if (inscripcion.getFechaInscripcion().before(excursion.getFecha())) {
                            bbdd.inscripcion.remove(i); // Eliminar la inscripción de la lista
                            return true;
                        } else {
                            // Si la fecha de inscripción es después de la fecha de la excursión, no se puede eliminar
                            return false;
                        }
                    }
                }
                // Si no se encontró la excursión correspondiente, no se puede determinar si la inscripción se puede eliminar
                return false;
            }
        }
        // Si no se encontró la inscripción con el número proporcionado
        return false;
    }


    // Getters
    public int getNumeroInscripcion() {
        return numeroInscripcion;
    }
    public int getNumeroSocio() {
        return numeroSocio;
    }
    public int getNumeroExcursion() {
        return numeroExcursion;
    }
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }
    // Setters
    public void setNumeroInscripcion(int numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }
    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }
    public void setNumeroExcursion(int numeroExcursion) {
        this.numeroExcursion = numeroExcursion;
    }
    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
    // Método toString
    @Override
    public String toString() {
        return "ID Inscripción: " + numeroInscripcion +
                " | ID Socio: " + numeroSocio +
                " | ID Escursión: " + numeroExcursion +
                " | Fecha Inscripción: " + fechaInscripcion;
    }

    public static String crearInscripcion(Datos BBDD, InscripcionModel inscripcion) {
        try {
            BBDD.inscripcion.add(inscripcion);
            return "Se guardo correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }

    public static String[] obtenerListadoInscipciones(Datos BBDD){
        String listado = "";
        int contador = 0;
        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            contador++;
            listado += "\n    - " + contador + ". " + inscripcion.toString();
        }
        if(contador == 0){
            listado = "- Sin datos.";
        }
        return new String[] {listado, String.valueOf(contador)};
    }

    public static FederacionModel obtenerFederacion(Datos BBDD, int seleccion){
        int contador = 0;
        for (FederacionModel federacion : BBDD.federacion) {
            contador++;
            if(contador == seleccion){
                return federacion;
            }
        }
        return null;
    }

    //Metodo para obtener inscripciones de un socio mediante numeroSocio
    public static String[] obtenerInscripcionesByNumSocio(Datos BBDD, int numSocio){
        double total = 0.0;
        String listado = "\n    - Lista de inscripciones del socio: ";
        int contador = 0;
        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            contador++;
            if(inscripcion.getNumeroSocio() == numSocio){
                Double precioExcursion = ExcursionModel.obtenerExcursionByCodigo(BBDD, inscripcion.getNumeroExcursion()).getPrecioInscripcion();
                String descripcionExcursion = ExcursionModel.obtenerExcursionByCodigo(BBDD, inscripcion.getNumeroExcursion()).getDescripcion();
                listado += "\n      - "+contador+". ID Inscripción: "+inscripcion.getNumeroInscripcion()+" | Precio excursion: "+precioExcursion+" | Descripcion excursion: "+descripcionExcursion;
                total += precioExcursion;
            }
        }
        if(contador == 0){
            listado = " - Sin inscripciones.";
        }
        return new String[] {listado, String.valueOf(total)};
    }

    //Metodo para comprobar si un usuario tiene inscripciones
    public static boolean comprobarSocioInscrito(Datos BBDD, int numSocio){
        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            if(inscripcion.getNumeroSocio() == numSocio){
                //Devuelve true si el socio esta inscrito en una excursión
                return true;
            }
        }
        return false;
    }
}
