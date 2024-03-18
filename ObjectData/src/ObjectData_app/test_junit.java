package ObjectData_app;
import org.junit.*;

import ObjectData_app.ObjectData_view.AppMenuView;
import ObjectData_app.ObjectData_view.SocioView;

public class test_junit {
    @Test
    public void testMenuInicioView() {
        AppMenuView vista = new AppMenuView();
        vista.menuInicioView();
    }
    @Test
    public void testMenuGestionSocioView() {
        SocioView vista = new SocioView();
        vista.formCrearSocioFederadoView();
    }
    
}
