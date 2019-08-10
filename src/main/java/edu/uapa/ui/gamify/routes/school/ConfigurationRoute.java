package edu.uapa.ui.gamify.routes.school;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.uapa.ui.gamify.views.school.configuration.ConfigurationFormDesign;
import edu.utesa.lib.models.dtos.configurations.ConfigurationDto;

import java.util.Arrays;
import java.util.List;

import static edu.uapa.ui.gamify.routes.AllRoutes.CONFIGURATION_ROUTE;

@Route(value = CONFIGURATION_ROUTE, layout = MainAppLayout.class)
public class ConfigurationRoute extends PageView {

    private VerticalLayout mainLayout = new VerticalLayout();
    private ConfigurationFormDesign doForm = new ConfigurationFormDesign();
    private ConfigurationDto dto = new ConfigurationDto();
    private List<Integer> questionQuantity = Arrays.asList(5, 10, 20, 30, 40, 50, 100, 150, 200, 500, 1000);
    private boolean hasSelect = false;

    public ConfigurationRoute() {
        initialized();
        if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Practicar")) {
            doForm.activeMode(false);
        }
    }

    private void initialized() {
        setMargin(false);
        setPadding(true);
        setSpacing(false);
        add(mainLayout);
        mainLayout.setMargin(false);
        mainLayout.setPadding(false);
        mainLayout.setSpacing(true);

        mainLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        mainLayout.add(header());
        mainLayout.add(doForm);
        mainLayout.add(footer());

        doForm.fillDifficulty();
        doForm.fillQuestion(questionQuantity);
        doForm.fillMode();
    }


    protected void collect() {
        doForm.collectData(dto);
    }

    private Component header() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        Span h2 = new Span();
        h2.getElement().setProperty("innerHTML", "<String><u>" + Captions.CONFIGURATION + "</u></strong>");
        h2.getStyle().set("color", "blue");
        h2.getStyle().set("font-size", "24px");
        h2.getStyle().set("margin-bottom", "20px");
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.add(h2);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, h2);
        return layout;
    }

    private Component footer() {
        HorizontalLayout layout = new HorizontalLayout();
        Button next = new Button("Next >>>");
        next.setWidth("120px");

        Button back = new Button("<<< Back");
        back.setWidth("120px");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("75%");
        verticalLayout.setMargin(false);
        verticalLayout.setSpacing(false);
        verticalLayout.setPadding(false);

        back.addClickListener(event -> Tools.navigateToTopic());
        next.addClickListener(event -> {
            if (doForm.validField()) {
                Tools.navigateToGame();
            } else {
                Notification.show("Debe selecionar un item para poder proseguir");
            }
        });

        layout.setWidthFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setPadding(false);

        layout.add(back);
        layout.add(verticalLayout);
        layout.add(next);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.getStyle().set("position", "absolute");
        layout.getStyle().set("bottom", "0px");
        layout.getStyle().set("left", "0px");

        return layout;
    }
}
