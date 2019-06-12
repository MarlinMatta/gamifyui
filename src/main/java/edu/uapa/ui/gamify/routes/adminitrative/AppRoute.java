package edu.uapa.ui.gamify.routes.adminitrative;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.utils.Tools;

import static edu.uapa.ui.gamify.routes.AllRoutes.PERMISSION_ROUTE;

@Route(value = PERMISSION_ROUTE, layout = MainAppLayout.class)
public class AppRoute extends PageView {

    private Tabs tabs = new Tabs();

    public AppRoute() {
        if (!Tools.isLogin()) {
            Tools.navigateToLogin();
        }
        setTabsManager(tabs);
        initialized();
    }

    private void initialized() {
        setMargin(false);
        setSpacing(false);
        add(getTabsManager());
        getTabsManager().addDefaultTab(new Icon(VaadinIcon.ACADEMY_CAP), "Academy", new Label("Hello Academy"));
    }
}
