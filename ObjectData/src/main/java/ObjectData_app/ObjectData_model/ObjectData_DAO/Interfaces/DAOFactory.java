package ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces;

public interface DAOFactory {
    SocioEstandarDAO instanciaSocioEstandarDAO();
    SocioFederadoDAO instanciaSocioFederadoDAO();
    SocioInfantilDAO instanciaSocioInfantilDAO();
    FederacionDAO instanciaFederacionDAO();
    ExcursionDAO instanciaExcursionDAO();
    InscripcionDAO instanciaInscripcionDAO();
}