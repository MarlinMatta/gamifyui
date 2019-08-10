package edu.uapa.ui.gamify.routes.school;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.requests.learn.LearnRequests;
import edu.uapa.ui.gamify.routes.AllRoutes;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.utils.Tools;
import edu.utesa.lib.models.dtos.school.LearnDto;

@Route(value = AllRoutes.LEARN_ROUTE, layout = MainAppLayout.class)
public class LearnRoute extends PageView {
    private VerticalLayout mainLayout;
    private LearnDto learnDto = LearnRequests.getInstance().getByTopicId(Tools.getSessionTopic());

    public LearnRoute() {
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
        mainLayout.add(body());
        mainLayout.add(footer());

        mainLayout.setAlignItems(Alignment.START);
        mainLayout.setJustifyContentMode(JustifyContentMode.START);
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.START);
        mainLayout.setHeight("90%");
    }

    private Component header() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        Span h2 = new Span();
        h2.getElement().setProperty("innerHTML", "<String><u>" + learnDto.getTopicDto().getName() + "</u></strong>");
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

    private Component body() {
        mainLayout = new VerticalLayout();
        Span span = new Span();
        span.setText(learnDto.getDescription());
        mainLayout.add(span);
        return mainLayout;
    }

    private Component footer() {
        HorizontalLayout layout = new HorizontalLayout();
//        Button next = new Button("Next >>>");
//        next.setWidth("120px");

        Button back = new Button("<<< Back");
        back.setWidth("120px");
        VerticalLayout klk = new VerticalLayout();
        klk.setWidth("75%");
        klk.setMargin(false);
        klk.setSpacing(false);
        klk.setPadding(false);

        back.addClickListener(event -> Tools.navigateToStudentMainMenu());
//        next.addClickListener(event -> Tools.navigateToStudentMainMenu());

        layout.setWidthFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setPadding(false);

        layout.add(back);
        layout.add(klk);
//        layout.add(next);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.getStyle().set("position", "absolute");
        layout.getStyle().set("bottom", "0px");
        layout.getStyle().set("left", "0px");

        return layout;
    }


}
