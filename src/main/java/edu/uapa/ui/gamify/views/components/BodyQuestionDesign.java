package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.Question;
import edu.uapa.ui.gamify.models.Response;

import java.util.Collections;
import java.util.List;

/**
 * A Designer generated component for the body-question-design.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("body-question-design")
@HtmlImport("src/views/components/body-question-design.html")
public class BodyQuestionDesign extends PolymerTemplate<BodyQuestionDesign.BodyQuestionDesignModel> {

    @Id("optionLayout")
    private RadioButtonGroup<Response> optionLayout;
    @Id("taQuestion")
    private TextArea taQuestion;

    /**
     * Creates a new BodyQuestionDesign.
     */
    public BodyQuestionDesign(Question question) {
        taQuestion.setValue(question.getQuestion());

        List<Response> res = question.getBadResponse();
        res.add(question.getResponse());
        Collections.shuffle(res);

        optionLayout.setItems(res);
        optionLayout.setRequired(true);
    }

    public boolean valid() {
        return optionLayout.getValue() != null;
    }

    /**
     * This model binds properties between BodyQuestionDesign and body-question-design.html
     */
    public interface BodyQuestionDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
