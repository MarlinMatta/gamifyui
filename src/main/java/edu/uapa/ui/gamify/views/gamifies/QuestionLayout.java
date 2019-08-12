package edu.uapa.ui.gamify.views.gamifies;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import edu.utesa.lib.models.dtos.school.ProblemDto;
import org.w3c.dom.events.EventTarget;

import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Designer generated component for the question-layout template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("question-layout")
@HtmlImport("src/views/gamifies/question-layout.html")
public class QuestionLayout extends PolymerTemplate<QuestionLayout.QuestionLayoutModel> {

    @Id("questionText")
    private Span questionText;
    @Id("questionProgress")
    private Span questionProgress;
    @Id("divAnswer01")
    private Element divAnswer01;
    @Id("divAnswer02")
    private Element divAnswer02;
    @Id("answer01")
    private Span answer01;
    @Id("divAnswer03")
    private Element divAnswer03;
    @Id("answer02")
    private Span answer02;
    @Id("answer03")
    private Span answer03;
    @Id("divAnswer04")
    private Element divAnswer04;
    @Id("answer04")
    private Span answer04;

    private ProblemDto problem;
    /**
     * Creates a new QuestionLayout.
     */
    public QuestionLayout(ProblemDto problem, int questionNumber, int questionQuantity) {
        this.problem = problem;

        questionText.setText(problem.getQuestion());
        questionProgress.setText(questionNumber + "/" + questionQuantity);
        List<String> answers = new ArrayList<>();
        answers.add(problem.getCorrectAnswer());
        answers.add(problem.getIncorrectAnswer01());
        answers.add(problem.getIncorrectAnswer02());
        answers.add(problem.getIncorrectAnswer03());
        Collections.shuffle(answers);

        answer01.setText(answers.get(0));
        answer02.setText(answers.get(1));
        answer03.setText(answers.get(2));
        answer04.setText(answers.get(3));
    }

    public Element getDivAnswer01() {
        return divAnswer01;
    }

    public Element getDivAnswer02() {
        return divAnswer02;
    }

    public Element getDivAnswer03() {
        return divAnswer03;
    }

    public Element getDivAnswer04() {
        return divAnswer04;
    }

    public ProblemDto getProblem() {
        return problem;
    }

    /**
     * This model binds properties between QuestionLayout and question-layout
     */
    public interface QuestionLayoutModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
