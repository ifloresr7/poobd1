package ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces;
import ObjectData_app.ObjectData_model.SocioEstandarModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SocioEstandarDAO {
    ArrayList<SocioEstandarModel> obtenerTodosSocioEstandar() throws SQLException;
    SocioEstandarModel obtenerSocioEstandarPorNumeroSocio(int numeroSocio) throws SQLException;
    void crearSocioEstandar(SocioEstandarModel socio) throws SQLException;
    void actualizarSocioEstandar(SocioEstandarModel socio) throws SQLException;
    void eliminarSocioEstandar(int numeroSocio) throws SQLException;
}