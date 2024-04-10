package ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces;
import ObjectData_app.ObjectData_model.InscripcionModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InscripcionDAO {
    ArrayList<InscripcionModel> obtenerTodasLasInscripciones() throws SQLException;
    ArrayList<InscripcionModel> obtenerTodasPorNumeroSocio(int numeroSocio) throws SQLException;
    void crearInscripcion(InscripcionModel excursion) throws SQLException;
    void eliminarInscripcion(int numeroInscripcion) throws SQLException;
}