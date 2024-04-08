package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.ExcursionDAO;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.InscripcionDAO;

public class InscripcionModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente
    // implementa la interfaz DAOFactory.
    static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de InscripcionDAO utilizando el objeto
    // factory.
    static InscripcionDAO inscripcionDAO = factory.instanciaInscripcionDAO();
    // Se crea una lista estática para almacenar objetos InscripcionModel.
    static ArrayList<InscripcionModel> inscripciones = new ArrayList<>();
    // Se obtiene una instancia estática de ExcursionDAO utilizando el objeto
    // factory.
    static ExcursionDAO excursionDAO = factory.instanciaExcursionDAO();
    // Se crea una lista estática para almacenar objetos ExcursionModel.
    static ArrayList<ExcursionModel> excursiones = new ArrayList<>();

    private int numeroInscripcion;
    private int numeroSocio;
    private int numeroExcursion;
    private Date fechaInscripcion;

    // Constructor
    public InscripcionModel(int numeroInscripcion, int numeroSocio, int numeroExcursion, Date fechaInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
        this.numeroSocio = numeroSocio;
        this.numeroExcursion = numeroExcursion;
        this.fechaInscripcion = fechaInscripcion;
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

    public static String[] listarInscripcionesFecha(String FechaI, String FechaF) throws SQLException, ParseException {
        // Se llama al DAO para obtener las inscripciones desde MySQL
        try {
            inscripciones = inscripcionDAO.obtenerTodasLasInscripciones();
        } catch (SQLException e) {
            // Implementar logica para devolver el error.
            throw new SQLException("No se ha podido obtener las inscripciones.");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Nuevo formato de fecha

        // Parsear las fechas de inicio y fin
        Date fechaInicio, fechaFin;
        try {
            fechaInicio = sdf.parse(FechaI);
            fechaFin = sdf.parse(FechaF);
        } catch (ParseException e) {
            // Mensaje de error si hay un problema con el formato de fecha
            throw new ParseException("Formato de fecha inválido. ", e.getErrorOffset());
        }

        StringBuilder listado = new StringBuilder();
        int contador = 0;

        for (InscripcionModel inscripcion : inscripciones) {
            Date fechaInscripcion = inscripcion.fechaInscripcion;
            if (fechaInscripcion.after(fechaInicio) && fechaInscripcion.before(fechaFin)) {
                String nombreExcursion = ExcursionModel.obtenerNombreExcursionPorNumeroExcursion(
                        inscripcion.getNumeroExcursion());
                String tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(inscripcion.getNumeroSocio());
                double precio = ExcursionModel.obtenerPrecioExcursion(inscripcion.getNumeroExcursion());
                String nombreSocio = "";
                double precioTotal = precio;
                String cadenaDescuento = "";

                if (tipoSocio.equals("Federado")) {
                    nombreSocio = SocioFederadoModel.getSocioFederadoNumeroSocio(inscripcion.getNumeroSocio())
                            .getNombre();
                    double descuento = precio * 0.1;
                    precioTotal -= descuento;
                    cadenaDescuento = "Se ha aplicado un 10% de descuento en la excursión. Precio real de la inscripción: "
                            + precioTotal + "\n";
                } else if (tipoSocio.equals("Estandar")) {
                    nombreSocio = SocioEstandarModel.getSocioEstandarNumeroSocio(inscripcion.getNumeroSocio())
                            .getNombre();
                    double precioSeguro = SocioEstandarModel
                            .obtenerPrecioSeguroPorNumeroSocio(inscripcion.getNumeroSocio());
                    precioTotal = precio + precioSeguro;
                    cadenaDescuento = "Precio del seguro contratado: " + precioSeguro + "\n"
                            + "Precio total de la inscripción: " + precioTotal;
                } else if (tipoSocio.equals("Infantil")) {
                    nombreSocio = SocioInfantilModel.getSocioInfantilNumeroSocio(inscripcion.getNumeroSocio())
                            .getNombre();
                    cadenaDescuento = "El socio no tiene descuentos a aplicar.\n";
                }

                contador++;
                listado.append("\n- ").append(contador).append(". Nombre del socio: ").append(nombreSocio)
                        .append(" | Identificador de inscripción: ").append(inscripcion.numeroInscripcion)
                        .append(" | Excursión: ").append(nombreExcursion)
                        .append(" | Fecha de inscripción: ").append(inscripcion.fechaInscripcion)
                        .append(" | Precio de la inscripción: ").append(precio)
                        .append("\n").append(cadenaDescuento);
            }
        }

        if (contador == 0) {
            listado.append("\n  - Sin datos.");
        }

        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    public static boolean eliminarInscripcionNumero(int num) throws SQLException {
        // Se llama al DAO para obtener las inscripciones y las excursiones desde MySQL
        try {
            inscripciones = inscripcionDAO.obtenerTodasLasInscripciones();
            excursiones = excursionDAO.obtenerTodasExcursiones();
        } catch (SQLException e) {
            // Implementar logica para devolver el error.
            throw new SQLException("No se han podido obtener los datos necesarios.");

        }
        for (int i = 0; i < inscripciones.size(); i++) {
            InscripcionModel inscripcion = inscripciones.get(i);
            if (inscripcion.getNumeroInscripcion() == num) {
                // Obtener el número de excursión de la inscripción
                int numExcursion = inscripcion.getNumeroExcursion();
                // Buscar la fecha de la excursión correspondiente en el array de excursiones
                for (ExcursionModel excursion : excursiones) {
                    if (excursion.getNumeroExcursion() == numExcursion) {
                        // Comparar la fecha de inscripción con la fecha de la excursión
                        if (inscripcion.getFechaInscripcion().before(excursion.getFecha())) {
                            inscripciones.remove(i); // Eliminar la inscripción de la lista
                            return true;
                        } else {
                            // Si la fecha de inscripción es después de la fecha de la excursión, no se
                            // puede eliminar
                            return false;
                        }
                    }
                }
                // Si no se encontró la excursión correspondiente, no se puede determinar si la
                // inscripción se puede eliminar
                return false;
            }
        }
        // Si no se encontró la inscripción con el número proporcionado
        return false;
    }

    // Metodo para crear inscripcion
    public static String crearInscripcion(InscripcionModel inscripcion) throws SQLException {
        try {
            inscripcionDAO.crearInscripcion(inscripcion);
            return "La inscripción se guardo correctamente!";
        } catch (SQLException error) {
            throw new SQLException("No se ha podido crear la inscripción;");
        }
    }

    public static String[] obtenerListadoInscripciones() throws SQLException {
        // Se llama al DAO para obtener las inscripciones desde MySQL
        try {
            inscripciones = inscripcionDAO.obtenerTodasLasInscripciones();
        } catch (SQLException e) {
            // Implementar logica para devolver el error.
            throw new SQLException("No se ha podido obtener el listado de inscripciones.");
        }
        // Atributos
        int contador = 0;
        String listado = "Listado de Inscripciones:";
        for (InscripcionModel inscripcion : inscripciones) {
            contador++;
            listado += contador + ". " + inscripcion.toString();
        }
        if (contador == 0) {
            listado += ("- Sin datos.");
        }
        return new String[] { listado };
    }

    // Metodo para obtener inscripciones de un socio mediante numeroSocio
    public static String[] obtenerInscripcionesByNumSocio(int numeroSocio) throws SQLException {
        // Se llama al DAO para obtener las inscripciones desde MySQL
        try {
            inscripciones = inscripcionDAO.obtenerTodasPorNumeroSocio(numeroSocio);
        } catch (SQLException e) {
            // Implementar logica para devolver el error.
            throw new SQLException("No se han podido obtener las inscripciones del socio seleccionado.");
        }
        double total = 0.0;
        String listado = "\n    - Lista de inscripciones del socio: ";
        int contador = 0;
        for (InscripcionModel inscripcion : inscripciones) {
            contador++;
            Double precioExcursion = ExcursionModel.obtenerExcursionPorNumeroExcursion(inscripcion.getNumeroExcursion())
                    .getPrecioInscripcion();
            String descripcionExcursion = ExcursionModel
                    .obtenerExcursionPorNumeroExcursion(inscripcion.getNumeroExcursion()).getDescripcion();
            listado += "\n      - " + contador + ". ID Inscripción: " + inscripcion.getNumeroInscripcion()
                    + " | Precio excursion: " + precioExcursion + " | Descripcion excursion: "
                    + descripcionExcursion;
            total += precioExcursion;
        }
        if (contador == 0) {
            listado = " - Sin inscripciones.";
        }
        return new String[] { listado, String.valueOf(total) };
    }

    // Metodo para comprobar si un usuario tiene inscripciones
    public static boolean comprobarSocioInscrito(int numeroSocio) throws SQLException {
        // Se llama al DAO para obtener las inscripciones desde MySQL
        try {
            inscripciones = inscripcionDAO.obtenerTodasPorNumeroSocio(numeroSocio);
        } catch (SQLException e) {
            // Implementar logica para devolver el error.
            throw new SQLException("No se han podido obtener las inscripciones para este socio.");
        }
        for (InscripcionModel inscripcion : inscripciones) {
            if (inscripcion.getNumeroSocio() == numeroSocio) {
                // Devuelve true si el socio esta inscrito en una excursión
                return true;
            }
        }
        return false;
    }
}
