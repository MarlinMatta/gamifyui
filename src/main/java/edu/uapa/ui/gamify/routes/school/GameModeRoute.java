package edu.uapa.ui.gamify.routes.school;

import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawerLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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

import static edu.uapa.ui.gamify.routes.AllRoutes.STUDENT_MAIN_MENU_ROUTE;

@Route(value = STUDENT_MAIN_MENU_ROUTE, layout = MainAppLayout.class)
public class GameModeRoute extends PageView {
    private VerticalLayout mainLayout;
    private AppDrawerLayout bodyLayout = new AppDrawerLayout();
    private boolean hasSelect = false;

    public GameModeRoute() {
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
        mainLayout.add(footer());

        mainLayout.setAlignItems(Alignment.START);
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        mainLayout.setHeight("90%");
    }

    private Component header() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        Span h2 = new Span();
        h2.getElement().setProperty("innerHTML", "<String><u>" + Captions.HELLO_STUDENT + "</u></strong>");
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

    private Component appLayoutBody() {
        bodyLayout.add(playComponent());
        bodyLayout.add(practiceComponent());
        bodyLayout.add(testComponent());
        bodyLayout.add(learnComponent());
        return bodyLayout;
    }

    private Component footer() {
        HorizontalLayout layout = new HorizontalLayout();
        Button next = new Button("Next", new Icon(VaadinIcon.ARROW_RIGHT));
        next.setIconAfterText(true);
        next.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        next.setWidth("120px");

        VerticalLayout klk = new VerticalLayout();
        klk.setWidth("75%");
        klk.setMargin(false);
        klk.setSpacing(false);
        klk.setPadding(false);

        next.addClickListener(event -> {
            if (hasSelect) {
                Tools.navigateToChooseSubject();
            } else {
                Notification.show("Debe llenar todos los campos para poder proseguir");
            }
        });

        layout.setWidthFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setPadding(false);

        layout.add(klk);
        layout.add(next);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.getStyle().set("position", "absolute");
        layout.getStyle().set("bottom", "0px");
        layout.getStyle().set("left", "0px");

        return layout;
    }

    private Component playComponent() {
        Button button = new Button(new Image("frontend/src/images/rompecabezas.png", Captions.PLAY));
        button.setId(Captions.PLAY);
        button.setWidth("200px");
        button.setHeight("100px");
        button.getStyle().set("margin", "2px");
        button.addClickListener(event -> {
            if (!event.getSource().getStyle().has("border")) {
                bodyLayout.getChildren().forEach(component -> {
                    ((Button) component).getStyle().remove("border");
                });
                event.getSource().getStyle().set("border", "3px solid cyan");
                VaadinSession.getCurrent().setAttribute(Tools.SESSION_GAME_MODE, "jugar");
                hasSelect = true;
            } else {
                hasSelect = false;
                event.getSource().getStyle().remove("border");
            }
        });
        return button;
    }

    private Component practiceComponent() {
        Button button = new Button(new Image("frontend/src/images/medalla.png", Captions.PRACTICE));
        button.setId(Captions.PRACTICE);
        button.setWidth("200px");
        button.setHeight("100px");
        button.getStyle().set("margin", "2px");
        button.addClickListener(event -> {
            if (!event.getSource().getStyle().has("border")) {
                bodyLayout.getChildren().forEach(component -> ((Button) component).getStyle().remove("border"));
                event.getSource().getStyle().set("border", "3px solid cyan");
                VaadinSession.getCurrent().setAttribute(Tools.SESSION_GAME_MODE, "Practicar");
                hasSelect = true;
            } else {
                hasSelect = false;
                event.getSource().getStyle().remove("border");
            }
        });
        return button;
    }

    private Component learnComponent() {
        Button button = new Button(new Image("frontend/src/images/lectura.png", Captions.LEARN));
        button.setId(Captions.LEARN);
        button.setWidth("200px");
        button.setHeight("100px");
        button.getStyle().set("margin", "2px");
        button.addClickListener(event -> {
            if (!event.getSource().getStyle().has("border")) {
                bodyLayout.getChildren().forEach(component -> {
                    ((Button) component).getStyle().remove("border");
                });
                event.getSource().getStyle().set("border", "3px solid cyan");
                VaadinSession.getCurrent().setAttribute(Tools.SESSION_GAME_MODE, "Aprender");
                hasSelect = true;
            } else {
                hasSelect = false;
                event.getSource().getStyle().remove("border");
            }
        });
        return button;
    }

    private Component testComponent() {
        Button button = new Button(new Image("frontend/src/images/aprendizaje.png", Captions.TEST));
        button.setId(Captions.TEST);
        button.setWidth("200px");
        button.setHeight("100px");
        button.getStyle().set("margin", "2px");
        button.addClickListener(event -> {
            if (!event.getSource().getStyle().has("border")) {
                bodyLayout.getChildren().forEach(component -> {
                    ((Button) component).getStyle().remove("border");
                });
                event.getSource().getStyle().set("border", "3px solid cyan");
                VaadinSession.getCurrent().setAttribute(Tools.SESSION_GAME_MODE, "Pruebas");
                hasSelect = true;
            } else {
                hasSelect = false;
                event.getSource().getStyle().remove("border");
            }
        });
        return button;
    }
}
