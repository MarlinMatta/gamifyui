package edu.uapa.ui.gamify.routes.school;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.routes.AllRoutes;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;

@Route(value = AllRoutes.LEARN_ROUTE, layout = MainAppLayout.class)
public class LearnRoute extends PageView {
    private VerticalLayout mainLayout;

    public LearnRoute() {
        initialized();
    }

    private void initialized() {
        setMargin(false);
        setPadding(true);
        setSpacing(false);

        buildMainLayout();
        add(mainLayout);
    }

    private void buildMainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);
        mainLayout.setPadding(false);
        mainLayout.getElement().getStyle().set("width", "100%");
        mainLayout.add(header());
        mainLayout.add(footer());

        mainLayout.setAlignItems(Alignment.END);
        mainLayout.setJustifyContentMode(JustifyContentMode.END);
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.END);
    }

    private Component header() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        Span h2 = new Span();
        h2.getElement().setProperty("innerHTML", "<String><u>" + Captions.LEARN + "</u></strong>");
        h2.getStyle().set("color", "blue");
        h2.getStyle().set("font-size", "24px");
        h2.getStyle().set("margin-bottom", "20px");
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.add(h2);
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setVerticalComponentAlignment(Alignment.CENTER, h2);
        return layout;
    }


    private Component footer() {
        HorizontalLayout layout = new HorizontalLayout();
        Button next = new Button("Next >>>");
        next.setWidth("120px");

        Button back = new Button("<<< Back");
        back.setWidth("120px");
        VerticalLayout klk = new VerticalLayout();
        klk.setWidth("75%");
        klk.setMargin(false);
        klk.setSpacing(false);
        klk.setPadding(false);

        back.addClickListener(event -> Tools.navigateToStudentMainMenu());
        next.addClickListener(event -> {
            Tools.navigateToStudentMainMenu();
        });

        layout.setWidthFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setPadding(false);

        layout.add(back);
        layout.add(klk);
        layout.add(next);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.getStyle().set("position", "absolute");
        layout.getStyle().set("bottom", "0px");
        layout.getStyle().set("left", "0px");

        return layout;
    }
}
