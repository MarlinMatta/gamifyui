package edu.uapa.ui.gamify.views.gamifies;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the answer-layout template.
 *
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

    /**
     * Creates a new AnswerLayout.
     */
    public AnswerLayout(String question, String correctAnswer, String studentAnswer) {
        this.question.setText(question);
        this.correctAnswer.setText("Correcto: " + correctAnswer);
        if (!studentAnswer.equals("")){
            this.studentAnswer.setText("Incorrecto: " + studentAnswer);
        } else this.studentAnswer.remove();
    }

    /**
     * This model binds properties between AnswerLayout and answer-layout
     */
    public interface AnswerLayoutModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
