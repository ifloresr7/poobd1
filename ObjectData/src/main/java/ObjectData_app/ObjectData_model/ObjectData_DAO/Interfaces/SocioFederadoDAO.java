package ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces;
import ObjectData_app.ObjectData_model.SocioFederadoModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SocioFederadoDAO {
    ArrayList<SocioFederadoModel> obtenerTodosSocioFederado() throws SQLException;
    SocioFederadoModel obtenerPorNumeroSocio(int numeroSocio) throws SQLException;
    void crearSocioFederado(SocioFederadoModel socio) throws SQLException;
    void eliminarSocioFederado(int numeroSocio) throws SQLException;
}