package edu.uapa.ui.gamify.routes.school;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.ui.charts.BubbleChartExample;
import edu.uapa.ui.gamify.utils.captions.Captions;

import static edu.uapa.ui.gamify.routes.AllRoutes.TEACHER_MAIN_MENU_ROUTE;

@Route(value = TEACHER_MAIN_MENU_ROUTE, layout = MainAppLayout.class)
public class TeacherDashboardRoute extends PageView {
    private VerticalLayout mainLayout;
    private Div bodyLayout = new Div();
    private boolean hasSelect = false;

    public TeacherDashboardRoute() {
        initialized();
    }

    private void initialized() {
        setMargin(false);
        setPadding(true);
        setSpacing(false);

        buildMainLayout();
        add(header());
        add(mainLayout);
    }

    private void buildMainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(true);
        mainLayout.setPadding(true);
        mainLayout.add(appLayoutBody());

        mainLayout.setAlignItems(Alignment.START);
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        mainLayout.setHeight("90%");
    }

    private Component header() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        Span h2 = new Span();
        h2.getElement().setProperty("innerHTML", "<String><u>" + Captions.HELLO_TEACHER + "</u></strong>");
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

    private Component appLayoutBody() {
        bodyLayout.add(new BubbleChartExample());
        return bodyLayout;
    }

//    private Component playComponent() {
//        VerticalLayout layout = new VerticalLayout();
//        BubbleChartExample bubbleChartExample = new BubbleChartExample();
//        layout.add(bubbleChartExample);
//
//        return layout;
//    }
}
