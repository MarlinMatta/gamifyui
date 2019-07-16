package edu.uapa.ui.gamify.ui.abstracts.base;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.VaadinSession;
import edu.uapa.ui.gamify.models.LoginManager;
import edu.uapa.ui.gamify.ui.components.TabsManager;
import edu.uapa.ui.gamify.utils.Tools;

public abstract class AbstractView extends HorizontalLayout {

    public AbstractView() {
        build();
    }

    protected static LoginManager getLoginManager() {
        return (LoginManager) VaadinSession.getCurrent().getAttribute(Tools.SESSION_NAME);
    }

    public TabsManager getTabsManager() {
        return (TabsManager) VaadinSession.getCurrent().getAttribute(Tools.SESSION_TAB_NAME);
    }

    private void build() {
        setSizeFull();
        setMargin(false);
        getElement().getStyle().set("overflow", "auto");
    }
}