package edu.uapa.ui.gamify.views.gamifies;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated component for the answer-layout template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("answer-layout")
@HtmlImport("src/views/gamifies/answer-layout.html")
public class AnswerLayout extends PolymerTemplate<AnswerLayout.AnswerLayoutModel> {

    @Id("correctAnswer")
    private Span correctAnswer;
    @Id("question")
    private Span question;
    @Id("studentAnswer")
    private Span studentAnswer;
    @Id("acquiredPoints")
    private Span acquiredPoints;

    /**
     * Creates a new AnswerLayout.
     */
    //Wrong answer
    public AnswerLayout(String question, String correctAnswer, String studentAnswer) {
        this.question.setText(question);
        this.correctAnswer.setText("Correcto: " + correctAnswer);
        this.studentAnswer.setText("Incorrecto: " + studentAnswer);
    }

    //Good answer
    public AnswerLayout(String question, String correctAnswer, int acquiredPoints) {
        this.question.setText(question);
        this.correctAnswer.setText("Correcto: " + correctAnswer);
        this.acquiredPoints.setText("Puntos adquiridos: " + acquiredPoints);
    }

    //Good answer practice
    public AnswerLayout(String question, String correctAnswer) {
        this.question.setText(question);
        this.correctAnswer.setText("Correcto: " + correctAnswer);
    }

    /**
     * This model binds properties between AnswerLayout and answer-layout
     */
    public interface AnswerLayoutModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
