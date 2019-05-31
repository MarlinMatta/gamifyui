package edu.uapa.ui.gamify.routes;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.utils.Constants;
import edu.uapa.ui.gamify.views.components.MainAppLayout;

@Route(value = Constants.APP_ROUTE, layout = MainAppLayout.class)
public class AppRoute extends VerticalLayout {

    public AppRoute() {
    }
}
