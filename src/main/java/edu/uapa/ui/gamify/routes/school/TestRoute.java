package edu.uapa.ui.gamify.routes.school;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
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
import edu.uapa.ui.gamify.views.gamifies.AnswerLayout;
import edu.uapa.ui.gamify.views.gamifies.QuestionLayout;
import edu.utesa.lib.models.dtos.school.ExamDto;
import edu.utesa.lib.models.dtos.school.ProblemAnswerDto;
import edu.utesa.lib.models.dtos.school.ProblemDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Route(value = AllRoutes.TEST_ROUTE, layout = MainAppLayout.class)
public class TestRoute extends PageView {

    private List<ExamDto> examList = ExamRequests.getInstance().getAll();
    private ExamDto exam = examList != null ? examList.get(0) : new ExamDto();

    private List<ProblemAnswerDto> result = new ArrayList<>();
    private List<ProblemDto> problems = new ArrayList<>(exam.getProblems());
    private VerticalLayout mainLayout;
    private LinkedList<Component> components = new LinkedList<>();
    private Component currentComponent;
    private Button goToInit;
    private final int problemQuantity = exam.getProblems().size();
    private final int pointsPerProblem = exam.getPoints() / problemQuantity;
    private int questionNumber = 1;
    private int points = 0;

    public TestRoute() {
        initialized();
    }

    private void initialized() {
        setMargin(false);
        setPadding(true);
        setSpacing(false);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        buildMainLayout();
        add(mainLayout);

        mainLayout.setAlignItems(Alignment.CENTER);
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        problems.forEach(problem -> {
            QuestionLayout questionLayout = new QuestionLayout(problem, questionNumber, problemQuantity);
            questionLayout.setId(problem.getId() + "");
            questionLayout.setVisible(false);
            questionLayout.setAction(event -> next(event.getSource().getText()));
            mainLayout.add(questionLayout);
            components.add(questionLayout);
            questionNumber++;
        });

        if (components.size() > 0) {
            currentComponent = components.getFirst();
            currentComponent.setVisible(true);
        }

        goToInit = new Button("Volver al inicio");
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

    private void next(String answer) {
        //Ultimo
        if (components.indexOf(currentComponent) == components.size() - 1) {
            components.forEach(component -> {
                ProblemAnswerDto problemAnswerDto = new ProblemAnswerDto();
                problemAnswerDto.setProblemDto(((QuestionLayout) component).getProblem());
                problemAnswerDto.setAnswer(((QuestionLayout) component).getAnswer());
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
        } else {
            currentComponent.setVisible(false);
            ((QuestionLayout) components.get(components.indexOf(currentComponent))).setAnswer(answer);
            currentComponent = components.get(components.indexOf(currentComponent) + 1);
            currentComponent.setVisible(true);
        }
    }

    private Component result() {
        setAlignItems(Alignment.START);
        mainLayout.setAlignItems(Alignment.START);

        VerticalLayout main = new VerticalLayout();
        main.setMargin(false);
        main.setPadding(false);
        main.setSpacing(false);

        result.forEach(answer -> {
            AnswerLayout answerLayout;
            String question = answer.getProblemDto().getQuestion();
            String correctAnswer = answer.getProblemDto().getCorrectAnswer();
            String studentAnswer = answer.getAnswer();
            int point = 0;

            if (answer.isGood()) {
                answerLayout = new AnswerLayout(question, correctAnswer);
                points += pointsPerProblem;
                point = pointsPerProblem;
            } else {
                answerLayout = new AnswerLayout(question, correctAnswer, answer.getAnswer());
                point = 0;
            }

            main.setWidthFull();
            main.add(answerLayout);
            main.add("Punto adquirido: " + point);
            main.add(new HorizontalLayout());
        });

        if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Play") || VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Pruebas")) {
            VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.setHeight("20px");
            main.add(verticalLayout);
            main.add("Total de puntos adquiridos: " + points);
            StudentRequests.getInstance().setPoint(getLoginManager().getId(), points);
        }
        return main;
    }
}
