package ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.Interfaces;
import ObjectData_app.ObjectData_model.FederacionModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FederacionDAO {
    ArrayList<FederacionModel> obtenerTodasFederaciones() throws SQLException;
    FederacionModel obtenerPorCodigo(String codigo) throws SQLException;
}