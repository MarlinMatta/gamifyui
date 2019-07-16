package edu.uapa.ui.gamify.routes.adminitrative;

import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.routes.AllRoutes;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.utils.Tools;

@Route(value = AllRoutes.APP_ROUTE, layout = MainAppLayout.class)
public class AppRoute extends PageView {

    private Tabs tabs = new Tabs();

    public AppRoute() {
        if (!Tools.isLogin()) {
            Tools.navigateToLogin();
        }
        initialized();
    }

    private void initialized() {
        setMargin(false);
        setSpacing(false);
        Tools.initTabs();
        add(getTabsManager());
    }
}
