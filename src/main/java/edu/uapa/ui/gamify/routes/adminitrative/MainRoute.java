package edu.uapa.ui.gamify.routes.adminitrative;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.utils.Tools;

import static edu.uapa.ui.gamify.routes.AllRoutes.MAIN_ROUTE;

@Route(MAIN_ROUTE)
public class MainRoute extends Div {
    public MainRoute() {
        if (Tools.isLogin()) Tools.navigateToApp();
        else Tools.navigateToLogin();
    }
}
