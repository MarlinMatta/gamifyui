package edu.uapa.ui.gamify.routes.security;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.ui.MainAppLayout;

import static edu.uapa.ui.gamify.routes.AllRoutes.PARAMETER_ROUTE;

@Route(value = PARAMETER_ROUTE, layout = MainAppLayout.class)
public class ParameterRoute extends VerticalLayout {

    public ParameterRoute() {
        initialized();
        setLanguage();
    }

    private void initialized() {
    }

    private void setLanguage() {

    }

}
