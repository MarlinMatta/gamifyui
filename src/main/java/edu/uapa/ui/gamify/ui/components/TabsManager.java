package edu.uapa.ui.gamify.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import edu.uapa.ui.gamify.ui.charts.BubbleChartExample;
import edu.uapa.ui.gamify.ui.charts.HorizontalBarChartExample;
import edu.uapa.ui.gamify.ui.charts.LineChartExample;
import edu.uapa.ui.gamify.ui.charts.VerticalBarChartExample;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class TabsManager extends VerticalLayout {
    private Tabs tabs;
    private Map<Tab, Component> tabsToPages = new HashMap<>();
    private Div pages = new Div();

    public TabsManager(Tabs tabs) {
        this.tabs = tabs;
        initialize();
    }

    private void initialize() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        tabs.setWidthFull();
        add(tabs);

        pages.getElement().getStyle().set("height", "94%");
        pages.getElement().getStyle().set("width", "100%");
        add(pages);

        defaultTab();
    }

    private void defaultTab() {
        addTab("Dashboard", appLayoutBody(), false);
        setAction();
    }

    private Component appLayoutBody() {
        FormLayout bodyLayout = new FormLayout();
        bodyLayout.add(new BubbleChartExample());
        bodyLayout.add(new HorizontalBarChartExample());
        bodyLayout.add(new VerticalBarChartExample());
        bodyLayout.add(new LineChartExample());
        bodyLayout.setWidth("80%");
        bodyLayout.setHeight("80%");
        return bodyLayout;
    }

    private void setAction() {
        tabs.addSelectedChangeListener(event -> {
            hideAllPage();
            Component selectedPage = tabsToPages.get(getSelectedTab());
            selectedPage.setVisible(true);
        });
    }

    private void hideAllPage() {
        tabsToPages.values().forEach(component -> component.setVisible(false));
    }

    public void addTab(String name, Component component, boolean closeable) {
        addTab(setTab(name, closeable), setBody(component));
    }

    private Tab setTab(String name, boolean closeable) {
        HorizontalLayout header = new HorizontalLayout();
        header.add(new Span(name));
        if (closeable) header.add(close());
        Tab tab = new Tab(header);
        tab.setId(name);
        return tab;
    }

    private Component setBody(Component component) {
        VerticalLayout body = new VerticalLayout();
        body.add(component);
        body.setHeightFull();
        body.setHorizontalComponentAlignment(Alignment.CENTER);
        return body;
    }

    private void addTab(Tab tab, Component component) {
        if (canAdd(tab)) {
            tabs.add(tab);
            pages.add(component);
            tabsToPages.put(tab, component);
            selectTab(tab);
        }
    }

    private boolean canAdd(Tab newTab) {
        AtomicBoolean result = new AtomicBoolean(true);
        tabsToPages.forEach((key, value) -> key.getId().ifPresent(tabName -> newTab.getId().ifPresent(newTabName -> {
            if (tabName.equals(newTabName)) {
                result.set(false);
                selectTab(key);
            }
        })));
        return result.get();
    }

    private void selectTab(Tab tab) {
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
}
