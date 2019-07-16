package edu.uapa.ui.gamify.routes.adminitrative;

import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.routes.AllRoutes;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.utils.Tools;

@Route(AllRoutes.MAIN_ROUTE)
public class MainRoute extends PageView {

    public MainRoute() {
        if (Tools.isLogin()) Tools.navigateToApp();
        else Tools.navigateToLogin();
    }

    private void initialized() {
        setMargin(false);
        setSpacing(false);
    }
}
