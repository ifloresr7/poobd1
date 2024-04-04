package ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces;
import ObjectData_app.ObjectData_model.ExcursionModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExcursionDAO {
    ArrayList<ExcursionModel> obtenerTodasExcursiones() throws SQLException;
    ExcursionModel obtenerPorNumeroExcursion(int numeroExcursion) throws SQLException;
    void crearExcursion(ExcursionModel excursion) throws SQLException;
    void eliminarExcursion(int numeroExcursion) throws SQLException;
}