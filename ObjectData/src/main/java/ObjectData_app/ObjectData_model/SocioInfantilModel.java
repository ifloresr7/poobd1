package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.Interfaces.SocioInfantilDAO;

public class SocioInfantilModel extends SocioModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente
    // implementa la interfaz DAOFactory.
    static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de SocioInfantilDAO utilizando el objeto
    // factory.
    static SocioInfantilDAO socioInfantilDAO = factory.instanciaSocioInfantilDAO();
    // Se crea una lista estática para almacenar objetos SocioInfantilModel.
    static ArrayList<SocioInfantilModel> sociosInfantiles = new ArrayList<>();

    private int numeroSocioPadreMadre;

    // Constructor
    public SocioInfantilModel(int numeroSocio, String nombre, int numeroSocioPadreMadre) {
        super(numeroSocio, nombre);
        this.numeroSocioPadreMadre = numeroSocioPadreMadre;
    }

    // Getter y Setter
    public int getNumeroSocioPadreMadre() {
        return numeroSocioPadreMadre;
    }

    public void setNumeroSocioPadreMadre(int numeroSocioPadreMadre) {
        this.numeroSocioPadreMadre = numeroSocioPadreMadre;
    }

    // Método toString
    @Override
    public String toString() {
        return "SocioInfantil{" +
                "numeroSocio=" + getNumeroSocio() +
                ", nombre='" + getNombre() + '\'' +
                ", numeroSocioPadreMadre=" + numeroSocioPadreMadre +
                '}';
    }

    // Metodos propios
    // Crear socio infantil
    public String crearSocioInfantil(SocioInfantilModel socio) {
        try {
            socioInfantilDAO.crearSocioInfantil(socio);
            return "Socio infantil guardado correctamente!";
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Metodo para listar los socios infantiles.
    public static String[] listarSocios(int valorInicialContador) {
        // Se obtienen los datos desde el DAO.
        try {
            sociosInfantiles = socioInfantilDAO.obtenerTodosSocioInfantil();
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        // Atributos.
        StringBuilder listado = new StringBuilder();
        int contador = valorInicialContador;
        for (SocioInfantilModel socio : sociosInfantiles) {
            contador++;
            listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio())
                    .append(" | Nombre: ").append(socio.getNombre()).append(" | Numero socio parental: ")
                    .append(socio.getNumeroSocioPadreMadre());
        }
        if (contador == 0) {
            listado.append("\n  - Sin datos de socios Infantiles.");
        }
        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    // Metodo para obtener socio infantil mediante numero de socio.
    public static SocioInfantilModel getSocioPorNumeroSocio(int numeroSocio) {
        try {
            return socioInfantilDAO.obtenerPorNumeroSocio(numeroSocio);
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Método para eliminar socio infantil de la base de datos
    public static boolean eliminarSocioModel(int numeroSocio) {
        try {
            socioInfantilDAO.eliminarSocioInfantil(numeroSocio);
            return true;
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
}
