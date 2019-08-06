package edu.uapa.ui.gamify.views.gamifies;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.ExamDto;
import edu.utesa.lib.models.dtos.school.SubjectDto;
import edu.utesa.lib.models.dtos.school.TopicDto;
import edu.utesa.lib.models.enums.ExamDifficulty;

import java.text.ParseException;

/**
 * A Designer generated component for the exam-form-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("exam-form-design")
@HtmlImport("src/views/gamifies/exam-form-design.html")
public class ExamFormDesign extends PolymerTemplate<ExamFormDesign.ExamFormDesignModel> implements FormStructure<ExamDto> {

    @Id("cbDifficulty")
    private ComboBox<ExamDifficulty> cbDifficulty;
    @Id("cbSubject")
    private ComboBox<SubjectDto> cbSubject;
    @Id("cbTopic")
    private ComboBox<TopicDto> cbTopic;
    @Id("cbProblemQuantity")
    private ComboBox<String> cbProblemQuantity;
    @Id("dpFromDate")
    private DatePicker dpFromDate;
    @Id("dpToDate")
    private DatePicker dpToDate;

    private SubjectDto subject;
    private TopicDto topic;

    private String[] problemQuantity = {"20","25","30","35","40"};

    /**
     * Creates a new ExamFormDesign.
     */
    public ExamFormDesign() {
        cbDifficulty.setLabel(Captions.DIFFICULTY);
        cbSubject.setLabel(Captions.SUBJECT);
        cbTopic.setLabel(Captions.TOPIC);
        cbProblemQuantity.setLabel(Captions.PROBLEM_QUANTITY);
        dpFromDate.setLabel(Captions.FROM_DATE);
        dpToDate.setLabel(Captions.TO_DATE);

        cbProblemQuantity.setItems(problemQuantity);

        subject = new SubjectDto();
        topic = new TopicDto();
    }

    @Override
    public void restore(ExamDto data) {

    }

    @Override
    public void visualize() {

    }

    @Override
    public boolean validField() {
        return false;
    }

    @Override
    public ExamDto collectData(ExamDto model) throws ParseException {
        return null;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between ExamFormDesign and exam-form-design
     */
    public interface ExamFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
