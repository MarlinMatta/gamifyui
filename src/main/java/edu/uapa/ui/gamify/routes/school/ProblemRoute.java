package edu.uapa.ui.gamify.routes.school;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.models.Question;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.QuestionGenerator;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.views.components.BodyQuestionDesign;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static edu.uapa.ui.gamify.routes.AllRoutes.PROBLEM_ROUTE;

@Route(value = PROBLEM_ROUTE, layout = MainAppLayout.class)
public class ProblemRoute extends PageView {

    private List<Question> questions = new QuestionGenerator().get();
    private List<Question> result = new ArrayList<>();
    private VerticalLayout mainLayout;
    private LinkedList<Component> components = new LinkedList<>();
    private Component currentComponent;

    public ProblemRoute() {
        initialized();
    }

    private void initialized() {
        setMargin(false);
        setPadding(true);
        setSpacing(false);
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        buildMainLayout();
        add(mainLayout);

        mainLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        mainLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        questions.forEach(question -> {
            BodyQuestionDesign design = new BodyQuestionDesign(question);
            design.setId(question.getId() + "");
            design.setVisible(false);
            mainLayout.add(design);
            components.add(design);
        });
        mainLayout.add(navigator());

        if (components.size() > 0) {
            currentComponent = components.getFirst();
            currentComponent.setVisible(true);
        }
    }

    private void buildMainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);
        mainLayout.setPadding(false);
        mainLayout.getElement().getStyle().set("width", "100%");
    }

    private Component navigator() {
        HorizontalLayout layout = new HorizontalLayout();
        Button back = new Button("<<< Back");
        Button jump = new Button("Jumps >>>");
        Button next = new Button("Next >>>");
        layout.add(back);
        layout.add(jump);
        layout.add(next);

        back.setEnabled(false);

        back.addClickListener(event -> {
            currentComponent.setVisible(false);
            currentComponent = components.get(components.indexOf(currentComponent) - 1);
            currentComponent.setVisible(true);

            if (components.indexOf(currentComponent) == 0) {
                back.setEnabled(false);
            } else {
                jump.setEnabled(true);
                next.setEnabled(true);
                next.setText("Next >>>");
            }
        });

        jump.addClickListener(event -> {
            currentComponent.setVisible(false);
            currentComponent = components.get(components.indexOf(currentComponent) + 1);
            currentComponent.setVisible(true);
            if (components.indexOf(currentComponent) == components.size() - 1) {
                jump.setEnabled(false);
                next.setText("Summit");
            } else {
                back.setEnabled(true);
                next.setEnabled(true);
                next.setText("Next>>>");
            }
        });

        next.addClickListener(event -> {
            if (((BodyQuestionDesign) currentComponent).valid()) {
                if (next.getText().equals("Summit")) {
                    components.forEach(component -> result.add(((BodyQuestionDesign) component).getResponse()));
                    currentComponent.setVisible(false);
                    currentComponent = result();
                    currentComponent.setVisible(true);
                }
                currentComponent.setVisible(false);
                currentComponent = components.get(components.indexOf(currentComponent) + 1);
                currentComponent.setVisible(true);
                if (components.indexOf(currentComponent) == components.size() - 1) {
                    next.setText("Summit");
                    jump.setEnabled(false);
                } else {
                    back.setEnabled(true);
                    jump.setEnabled(true);
                    next.setText("Next >>>");
                }
            } else {
                Notification.show("Tienes que selecionar una repuestas");
            }
        });

        return layout;
    }

    private Component result() {
        VerticalLayout main = new VerticalLayout();
        Span question = new Span();
        Span response = new Span();

        question.getStyle().set("color", "blue");
        question.getStyle().set("font-size", "24px");
        question.getStyle().set("width", "95%");

        response.getStyle().set("color", "black");
        response.getStyle().set("font-size", "18px");
        response.getStyle().set("margin-left", "20px");
        response.getStyle().set("width", "95%");


        result.forEach(question1 -> {
            if (question1.isGood()) {
                question.getElement().getStyle().set("border", "1 px solid green");
            } else {
                question.getElement().getStyle().set("border", "1 px solid red");
            }
            question.getElement().setProperty("innerHTML", "<String><u>" + question1.getQuestion() + "</u></strong>");
            response.getElement().setProperty("innerHTML", "<String><u>" + question1.getResponse() + "</u></strong>");
            main.add();
        });

        return main;
    }
}
