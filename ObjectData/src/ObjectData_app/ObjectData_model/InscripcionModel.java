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
    public static String[] listarInscripcionesFecha(Datos BBDD, String FechaI,String FechaF)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato de fecha

        // Parsear las fechas de inicio y fin
        Date fechaInicio, fechaFin;
        try {
            fechaInicio = sdf.parse(FechaI);
            fechaFin = sdf.parse(FechaF);
        } catch (ParseException e) {
            return new String[]{"Error en el formato de fecha.", "0"};
        }
        String listado = "";
        int contador = 0;

        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            Date fechaInscripcion = inscripcion.getFechaInscripcion();
            if (fechaInscripcion.after(fechaInicio) && fechaInscripcion.before(fechaFin)) {
                String nombreExcursion = ExcursionModel.obtenerNombreExcursionPorId(BBDD, inscripcion.getNumeroExcursion());
                String nombreSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD,inscripcion.numeroSocio);
                contador++;
                listado += "\n    - " + contador + ".Nombre del socio: " + nombreSocio + " | Identificador de inscripción: " + inscripcion.getNumeroInscripcion() + " | Excursión: "
                         + nombreExcursion + " | Fecha de inscripcion: " + inscripcion.getFechaInscripcion();
            }
        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }
    public static String[] listarInscripciones(Datos BBDD)
    {
        String listado = "";
        int contador = 0;
        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            String nombreExcursion = ExcursionModel.obtenerNombreExcursionPorId(BBDD, inscripcion.getNumeroExcursion());
            String nombreSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD,inscripcion.numeroSocio);
            contador++;
            listado += "\n    - " + contador + ".Nombre del socio: " + nombreSocio + " | Identificador de inscripción: " + inscripcion.getNumeroInscripcion() + " | Excursión: "
                    + nombreExcursion + " | Fecha de inscripcion: " + inscripcion.getFechaInscripcion() ;
        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
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
        String listado = "";
        int contador = 0;
        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            contador++;
            if(inscripcion.getNumeroSocio() == numSocio){
                Double precioExcursion = ExcursionModel.obtenerExcursion(BBDD, inscripcion.getNumeroExcursion()).getPrecioInscripcion();
                String descripcionExcursion = ExcursionModel.obtenerExcursion(BBDD, inscripcion.getNumeroExcursion()).getDescripcion();
                listado = "\n   - "+contador+". ID Inscripción: "+inscripcion.getNumeroInscripcion()+" | Precio excursion: "+precioExcursion+" | Descripcion excursion: "+descripcionExcursion;
                total += precioExcursion;
            }
        }
        if(contador == 0){
            listado = " - Sin inscripciones.";
        }
        return new String[] {listado, String.valueOf(total)};
    }

    //Metodo para comprobar si un usuario tiene inscripciones
    public static boolean comprobarIncripcionPorNumeroSocio(Datos BBDD, int numSocio){
        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            if(inscripcion.getNumeroSocio() == numSocio){
                return true;
            }
        }
        return false;
    }
}
