package edu.uapa.ui.gamify.ui.abstracts;

import com.vaadin.flow.component.tabs.Tabs;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.ui.components.TabsManager;

public abstract class PageView extends AbstractView {

    private TabsManager tabsManager;

    public PageView() {
        setSizeFull();
        setAlignItems(Alignment.START);
        setJustifyContentMode(JustifyContentMode.START);
    }

    public TabsManager getTabsManager() {
        return tabsManager;
    }

    public void setTabsManager(Tabs tabs) {
        tabsManager = new TabsManager(tabs);
    }
}