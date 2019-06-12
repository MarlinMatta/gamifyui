package edu.uapa.ui.gamify.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TabsManager extends VerticalLayout {
    private Tabs tabs;
    private Set<Component> pagesShown;
    private Map<Tab, Component> tabsToPages = new HashMap<>();
    private Div pages = new Div();

    public TabsManager(Tabs tabs) {
        this.tabs = tabs;
        initialize();
    }

    private void initialize() {
        setWidthFull();
        tabs.setWidthFull();
        pages.setSizeFull();

        add(tabs);
        add(pages);
//        setAction();
    }

    public void setAction() {
        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });
    }

    public void addTab(Tab tab) {
        tabs.add(tab);
    }

    public void addDefaultTab(Icon icon, String name, Component component) {
        addTab(icon, name, component);
        pagesShown = Stream.of(component)
                .collect(Collectors.toSet());
        setAction();
    }

    public void addTab(Icon icon, String name, Component component) {
        HorizontalLayout header = new HorizontalLayout();
        header.add(new Span(name));
        header.add(close());
        Tab tab = new Tab(header);

        VerticalLayout body = new VerticalLayout();
        body.add(component);
        body.setSizeFull();
        body.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        addTab(tab, body);
    }

    private void addTab(Tab tab, Component component) {
        tabs.add(tab);
        pages.add(component);
        tabsToPages.put(tab, component);
        selectTab(tab);
    }

    public void setSmallTheme() {
        tabs.addThemeVariants(TabsVariant.LUMO_SMALL);
    }

    public void setEqualWithTabsTheme() {
        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
    }

    public void selectTab(Tab tab) {
        tabs.setSelectedTab(tab);
    }

    private Tab getSelectedTab() {
        return tabs.getSelectedTab();
    }

    private void closeTab(Tab tab, Component body) {
        tabs.remove(tab);
        pages.remove(body);
        tabsToPages.remove(tab);
    }

    private Span close() {
        Span span = new Span();
        span.getElement().setProperty("innerHTML", "<String>x</strong>");
        span.getStyle().set("color", "red");
        span.addClickListener(e -> closeTab(getSelectedTab(), tabsToPages.get(getSelectedTab())));
        return span;
    }

    public Tabs getTabs() {
        return tabs;
    }

    public Div getPages() {
        return pages;
    }
}
