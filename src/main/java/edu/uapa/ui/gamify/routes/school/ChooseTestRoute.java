package edu.uapa.ui.gamify.routes.school;

import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawerLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import edu.uapa.ui.gamify.requests.gamifies.ExamRequests;
import edu.uapa.ui.gamify.requests.gamifies.TopicRequests;
import edu.uapa.ui.gamify.routes.AllRoutes;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.ExamDto;
import edu.utesa.lib.models.dtos.school.TopicDto;

import java.util.List;

@Route(value = AllRoutes.CHOOSE_TEST_ROUTE, layout = MainAppLayout.class)
public class ChooseTestRoute extends PageView {

    private VerticalLayout mainLayout;
    private AppDrawerLayout bodyLayout = new AppDrawerLayout();
    private List<ExamDto> exams = ExamRequests.getInstance().getFiltered(Tools.getSessionSubject(), Tools.getSessionTopic());
    private boolean hasSelect = false;

    public ChooseTestRoute() {
        initialized();
    }

    private void initialized() {
        setMargin(true);
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
        h2.getElement().setProperty("innerHTML", "<String><u>" + "Tema: " + exams.get(0).getTopicDto().getName() + "</u></strong>");
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
        exams.forEach(exam -> {
            Component component = subjectComponent("Profesor: " + exam.theTeacherName() + " - Puntos: " + exam.getPoints());
            component.setId(exam.getId() + "");
            bodyLayout.add(component);
        });
        return bodyLayout;
    }

    private Component footer() {
        HorizontalLayout layout = new HorizontalLayout();
        Button next = new Button("Siguiente >>>");
        next.setWidth("130px");

        Button back = new Button("<<< Atrás");
        back.setWidth("120px");
        VerticalLayout klk = new VerticalLayout();
        klk.setWidth("75%");
        klk.setMargin(false);
        klk.setSpacing(false);
        klk.setPadding(false);

        back.addClickListener(event -> {
            if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Aprender")) {
                Tools.navigateToChooseSubject();
            } else if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Practicar")) {
                Tools.navigateToChooseSubject();
            } else if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Pruebas")) {
                Tools.navigateToChooseSubject();
            } else if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Play")) {
                Tools.navigateToChooseSubject();
            } else {
                Tools.navigateToChooseSubject();
            }
        });
        next.addClickListener(event -> {
            if (hasSelect) {
                if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Aprender")) {
                    Tools.navigateToLearn();
                } else if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Practicar")) {
                    Tools.navigateToConfiguration();
                } else if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Pruebas")) {
                    Tools.navigateToTest();
                } else if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Play")) {
                    Tools.navigateToConfiguration();
                } else {
                    System.out.println(VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE) + "");
//                    Tools.navigateToStudentMainMenu();
                }
            } else {
                Notification.show("Debe selecionar un item para poder proseguir");
            }
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

    private Component subjectComponent(String name) {
        Button button = new Button(name);
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        button.setId(name);
        button.setWidth("400px");
        button.setHeight("100px");
        button.getStyle().set("margin", "2px");
        button.getStyle().set("border", "3px solid magenta");
        button.addClickListener(event -> {
            if (event.getSource().getIcon() == null) {
                bodyLayout.getChildren().forEach(component -> ((Button) component).setIcon(null));
                event.getSource().setIcon(new Icon(VaadinIcon.CHECK));
                Tools.setSessionExam(button.getId().orElse("1"));
                hasSelect = true;
            } else {
                hasSelect = false;
                event.getSource().setIcon(null);
            }
        });
        return button;
    }
}
