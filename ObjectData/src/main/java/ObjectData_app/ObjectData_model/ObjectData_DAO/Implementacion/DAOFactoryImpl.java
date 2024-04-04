package ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.*;

public class DAOFactoryImpl implements DAOFactory {

    @Override
    public SocioEstandarDAO instanciaSocioEstandarDAO() {
        return new SocioEstandarDAOImpl(); // Suponiendo que SocioEstandarDAOImpl es la implementación concreta de SocioEstandarDAO
    }

    @Override
    public SocioFederadoDAO instanciaSocioFederadoDAO() {
        return new SocioFederadoDAOImpl(); // Implementa SocioFederadoDAOImpl de manera similar
    }

    @Override
    public SocioInfantilDAO instanciaSocioInfantilDAO() {
        return new SocioInfantilDAOImpl(); // Implementa SocioInfantilDAOImpl de manera similar
    }

    @Override
    public FederacionDAO instanciaFederacionDAO() {
        return new FederacionDAOImpl(); // Implementa FederacionDAOImpl de manera similar
    }

    @Override
    public ExcursionDAO instanciaExcursionDAO() {
        return new ExcursionDAOImpl(); // Implementa ExcursionDAOImpl de manera similar
    }

    @Override
    public InscripcionDAO instanciaInscripcionDAO() {
        return new InscripcionDAOImpl(); // Implementa InscripcionDAOImpl de manera similar
    }
}