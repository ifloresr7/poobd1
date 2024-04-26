package ObjectData_app.ObjectData_model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
        StringBuilder listado = new StringBuilder();
        int contador = 0;
    
        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            Date fechaInscripcion = inscripcion.fechaInscripcion;
            if (fechaInscripcion.after(fechaInicio) && fechaInscripcion.before(fechaFin)) {
                String nombreExcursion = ExcursionModel.obtenerNombreExcursionPorId(BBDD, inscripcion.getNumeroExcursion());
                String tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD,inscripcion.getNumeroSocio());
                String nombreSocio = SocioModel.obtenerNombreSocio(BBDD, inscripcion.getNumeroSocio());
                double precio = ExcursionModel.obtenerPrecioExcursion(BBDD, inscripcion.getNumeroExcursion());
                double precioTotal = precio;
                String cadenaDescuento = "";
    
                if (tipoSocio.equals("Federado")) {
                    double descuento = precio * 0.1;
                    precioTotal -= descuento;
                    cadenaDescuento = "Se ha aplicado un 10% de descuento en la excursión. Precio real de la inscripción: " + precioTotal + "\n";
                } else if (tipoSocio.equals("Estandar")) {
                    double precioSeguro = SocioEstandarModel.obtenerPrecioSeguro(BBDD, nombreSocio);
                    precioTotal = precio + precioSeguro;
                    cadenaDescuento = "Precio del seguro contratado: " + precioSeguro + "\n" + "Precio total de la inscripción: " + precioTotal;
                } else if (tipoSocio.equals("Infantil")) {
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
    
        return new String[]{listado.toString(), String.valueOf(contador)};
    }
    

    public static String[] listarInscripciones(Datos BBDD, int num)
    {

        StringBuilder listado = new StringBuilder();
        int contador = 0;
        for (InscripcionModel inscripcion : BBDD.inscripcion) {
            if (inscripcion.getNumeroSocio() == num) {
            String nombreExcursion = ExcursionModel.obtenerNombreExcursionPorId(BBDD, inscripcion.getNumeroExcursion());
            String tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD, inscripcion.getNumeroSocio());
            String nombreSocio = SocioModel.obtenerNombreSocio(BBDD, inscripcion.getNumeroSocio());
            double precio = ExcursionModel.obtenerPrecioExcursion(BBDD, inscripcion.getNumeroExcursion());
            String cadenaDescuento = "";
            double precioTotal = precio;
            if (tipoSocio.equals("Federado"))
            {
                double descuento = precio * 0.1;
                precioTotal -= descuento;
                cadenaDescuento = "Se ha aplicado un 10% de descuento en la excursión. Precio real de la inscripción: " + precioTotal + "\n";
            } 
            else if (tipoSocio.equals("Estandar")) 
            {
                double precioSeguro = SocioEstandarModel.obtenerPrecioSeguro(BBDD, nombreSocio);
                precioTotal = precio + precioSeguro;
                cadenaDescuento = "Precio del seguro contratado: " + precioSeguro + "\n" + "Precio total de la inscripción: " + precioTotal;
            } else if (tipoSocio.equals("Infantil")) 
            {
                cadenaDescuento = "El socio no tiene descuentos a aplicar.\n";
            }
            listado.append("\n").append(contador+1).append(". Nombre del socio: ").append(nombreSocio)
                    .append(" \nIdentificador de inscripción: ").append(inscripcion.getNumeroInscripcion())
                    .append(" \nExcursión: ").append(nombreExcursion)
                    .append(" \nFecha de inscripción: ").append(inscripcion.getFechaInscripcion())
                    .append(" \nPrecio de la excursión: ").append(precio)
                    .append("\n").append(cadenaDescuento);
            contador++;
        }
    }
    if (contador == 0) 
    {
        listado.append("\n  - Sin datos.");
    }
    return new String[] { listado.toString(), String.valueOf(contador) };
}
    public static boolean eliminarInscripcionNumero(Datos bbdd, int num) 
    {
        
        for (int i = 0; i < bbdd.inscripcion.size(); i++) 
        {
            InscripcionModel inscripcion = bbdd.inscripcion.get(i);
            if (inscripcion.getNumeroInscripcion() == num) 
            {
                // Obtener el número de excursión de la inscripción
                int numExcursion = inscripcion.getNumeroExcursion();
                // Buscar la fecha de la excursión correspondiente en el array de excursiones
                for (ExcursionModel excursion : bbdd.excursion) 
                {
                    if (excursion.getNumeroExcursion() == numExcursion) 
                    {
                        // Comparar la fecha de inscripción con la fecha de la excursión
                        if (inscripcion.getFechaInscripcion().before(excursion.getFecha())) 
                        {
                            bbdd.inscripcion.remove(i); // Eliminar la inscripción de la lista
                            return true;
                        } 
                        else
                        {
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
    public int getNumeroInscripcion() 
    {
        return numeroInscripcion;
    }
    public int getNumeroSocio() 
    {
        return numeroSocio;
    }
    public int getNumeroExcursion() 
    {
        return numeroExcursion;
    }
    public Date getFechaInscripcion() 
    {
        return fechaInscripcion;
    }
    // Setters
    public void setNumeroInscripcion(int numeroInscripcion) 
    {
        this.numeroInscripcion = numeroInscripcion;
    }
    public void setNumeroSocio(int numeroSocio)
    {
        this.numeroSocio = numeroSocio;
    }
    public void setNumeroExcursion(int numeroExcursion) 
    {
        this.numeroExcursion = numeroExcursion;
    }
    public void setFechaInscripcion(Date fechaInscripcion) 
    {
        this.fechaInscripcion = fechaInscripcion;
    }
    // Método toString
    @Override
    public String toString() 
    {
        return "ID Inscripción: " + numeroInscripcion +
                " | ID Socio: " + numeroSocio +
                " | ID Escursión: " + numeroExcursion +
                " | Fecha Inscripción: " + fechaInscripcion;
    }

    public static String crearInscripcion(Datos BBDD, InscripcionModel inscripcion) 
    {
        try 
        {
            BBDD.inscripcion.add(inscripcion);
            return "Se guardo correctamente!";
        } 
        catch (Exception error) 
        {
            return "Fallo al guardar: " + error;
        }
    }

    public static String[] obtenerListadoInscripciones(Datos BBDD) {
    List<String> inscripciones = new ArrayList<>();
    int contador = 0;
    for (InscripcionModel inscripcion : BBDD.inscripcion) {
        contador++;
        inscripciones.add(contador + ". " + inscripcion.toString());
    }
    if (contador == 0) {
        inscripciones.add("- Sin datos.");
    }
    return inscripciones.toArray(new String[0]);
}


    public static FederacionModel obtenerFederacion(Datos BBDD, int seleccion)
    {
        int contador = 0;
        for (FederacionModel federacion : BBDD.federacion) 
        {
            contador++;
            if(contador == seleccion)
            {
                return federacion;
            }
        }
        return null;
    }

    //Metodo para obtener inscripciones de un socio mediante numeroSocio
    public static String[] obtenerInscripcionesByNumSocio(Datos BBDD, int numSocio)
    {
        double total = 0.0;
        String listado = "";
        int contador = 0;
        for (InscripcionModel inscripcion : BBDD.inscripcion) 
        {
            contador++;
            if(inscripcion.getNumeroSocio() == numSocio)
            {
                Double precioExcursion = ExcursionModel.obtenerExcursion(BBDD, inscripcion.getNumeroExcursion()).getPrecioInscripcion();
                String descripcionExcursion = ExcursionModel.obtenerExcursion(BBDD, inscripcion.getNumeroExcursion()).getDescripcion();
                listado = "\n   - "+contador+". ID Inscripción: "+inscripcion.getNumeroInscripcion()+" | Precio excursion: "+precioExcursion+" | Descripcion excursion: "+descripcionExcursion;
                total += precioExcursion;
            }
        }
        if(contador == 0)
        {
            listado = " - Sin inscripciones.";
        }
        return new String[] {listado, String.valueOf(total)};
    }

    //Metodo para comprobar si un usuario tiene inscripciones
    public static boolean comprobarSocioInscrito(Datos BBDD, int numSocio)
    {
        for (InscripcionModel inscripcion : BBDD.inscripcion) 
        {
            if(inscripcion.getNumeroSocio() == numSocio)
            {
                //Devuelve true si el socio esta inscrito en una excursión
                return true;
            }
        }
        return false;
    }
}
