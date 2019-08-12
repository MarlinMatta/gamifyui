package edu.uapa.ui.gamify.routes.school;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import edu.uapa.ui.gamify.requests.gamifies.ExamRequests;
import edu.uapa.ui.gamify.requests.school.StudentRequests;
import edu.uapa.ui.gamify.routes.AllRoutes;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.views.components.BodyQuestionDesign;
import edu.utesa.lib.models.dtos.school.ExamDto;
import edu.utesa.lib.models.dtos.school.ProblemAnswerDto;
import edu.utesa.lib.models.dtos.school.ProblemDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Route(value = AllRoutes.TEST_ROUTE, layout = MainAppLayout.class)
public class TestRoute extends PageView {
    private List<ExamDto> exam = ExamRequests.getInstance().getAll();
    private List<ProblemAnswerDto> result = new ArrayList<>();
    private List<ProblemDto> problems = new ArrayList<>(exam.get(0).getProblems());
    private VerticalLayout mainLayout;
    private LinkedList<Component> components = new LinkedList<>();
    private Component currentComponent;
    private Button goToInit;
    private int pointsPerProblem = exam.get(0).getPoints() / exam.get(0).getProblemQuantity();
    private int points = 0;

    public TestRoute() {
        initialized();
    }

    private void initialized() {
        setMargin(false);
        setPadding(true);
        setSpacing(false);
        setAlignItems(Alignment.START);
        setJustifyContentMode(JustifyContentMode.START);

        buildMainLayout();
        add(mainLayout);

        mainLayout.setAlignItems(Alignment.START);
        mainLayout.setJustifyContentMode(JustifyContentMode.START);

        problems.forEach(problem -> {
            BodyQuestionDesign design = new BodyQuestionDesign(problem);
            design.setId(problem.getId() + "");
            design.setVisible(false);
            mainLayout.add(design);
            components.add(design);
        });
        mainLayout.add(navigator());

        if (components.size() > 0) {
            currentComponent = components.getFirst();
            currentComponent.setVisible(true);
        }

        goToInit = new Button("<-- Volver al inicio");
        goToInit.setWidth("200px");
        goToInit.addClickListener(event -> Tools.navigateToStudentMainMenu());

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
        Button back = new Button("<<< Volver");
        Button jump = new Button("Saltar >>>");
        Button next = new Button("Proximo >>>");
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
                next.setText("Proximo >>>");
            }
        });

        next.addClickListener(event -> {
            if (((BodyQuestionDesign) currentComponent).valid()) {
                if (next.getText().equals("Summit")) {
                    components.forEach(component -> {
                        ProblemAnswerDto problemAnswerDto = new ProblemAnswerDto();
                        problemAnswerDto.setProblemDto(((BodyQuestionDesign) component).getProblem());
                        problemAnswerDto.setAnswer(((BodyQuestionDesign) component).getResponse());
                        if (problemAnswerDto.getProblemDto().getCorrectAnswer().equals(problemAnswerDto.getAnswer())) {
                            problemAnswerDto.setGood(true);
                        } else {
                            problemAnswerDto.setGood(false);
                        }
                        result.add(problemAnswerDto);
                    });
                    removeAll();
                    add(result());
                    add(goToInit);
                    back.setVisible(false);
                    jump.setVisible(false);
                    next.setVisible(false);
                    Notification.show("Si se realizo");
                } else {
                    currentComponent.setVisible(false);
                    currentComponent = components.get(components.indexOf(currentComponent) + 1);
                    currentComponent.setVisible(true);
                    if (components.indexOf(currentComponent) == components.size() - 1) {
                        next.setText("Summit");
                        jump.setEnabled(false);
                    } else {
                        back.setEnabled(true);
                        jump.setEnabled(true);
                        next.setText("Proximo >>>");
                    }
                }
            } else {
                Notification.show("Tienes que selecionar una repuestas");
            }
        });

        if (components.indexOf(currentComponent) == components.size() - 1) {
            jump.setEnabled(false);
            next.setText("Summit");
        } else {
            back.setEnabled(true);
            next.setEnabled(true);
            next.setText("Proximo >>>");
        }

        return layout;
    }

    private Component result() {
        setAlignItems(Alignment.START);
        mainLayout.setAlignItems(Alignment.START);

        VerticalLayout main = new VerticalLayout();
        main.setMargin(false);
        main.setPadding(false);
        main.setSpacing(false);

        result.forEach(answer -> {
            Span question = new Span();
            Span response = new Span();
            question.getStyle().set("font-size", "24px");
            question.getStyle().set("width", "95%");

            response.getStyle().set("color", "black");
            response.getStyle().set("font-size", "18px");
            response.getStyle().set("margin-left", "20px");
            response.getStyle().set("width", "95%");

            question.getElement().setProperty("innerHTML", "<String>" + answer.getProblemDto().getQuestion() + "</strong>");
            response.getElement().setProperty("innerHTML", "<String>" + answer.getAnswer() + "</strong>");

            if (answer.isGood()) {
                points += pointsPerProblem;
                question.getStyle().set("color", "green");
                main.add("Punto adquirido: " + points);
            } else {
                question.getStyle().set("color", "red");
                main.add("Punto adquirido: " + 0);
            }

            main.setWidthFull();
            main.getStyle().set("border-style", "double");
            main.add(question);
            main.add(response);
            main.add(new HorizontalLayout());
        });

        if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Play")) {
            VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.setHeight("20px");
            main.add(verticalLayout);
            main.add("Total de punto adquirido: " + points);
            StudentRequests.getInstance().setPoint(getLoginManager().getId(), points);
        }
        return main;
    }
}
