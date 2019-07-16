package edu.uapa.ui.gamify.routes.school;

import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;

import static edu.uapa.ui.gamify.routes.AllRoutes.SUMMIT_RESULT;

@Route(value = SUMMIT_RESULT, layout = MainAppLayout.class)
public class SummitResultRoute extends PageView {

    public SummitResultRoute() {
        initialized();
    }

    private void initialized() {

    }
}
