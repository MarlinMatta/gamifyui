package edu.uapa.ui.gamify.views.school.topic;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.utesa.lib.models.dtos.school.GradeDto;
import edu.utesa.lib.models.dtos.school.SubjectDto;
import edu.utesa.lib.models.dtos.school.TopicDto;

/**
 * A Designer generated component for the topic-form-design.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("topic-form-design")
@HtmlImport("src/views/school/topic-form-design.html")
public class TopicFormDesing extends PolymerTemplate<TopicFormDesing.TopicFormDesingModel> implements FormStructure<TopicDto> {

    @Id("tfName")
    private TextField tfName;
    @Id("tfDescription")
    private TextField tfDescription;
    @Id("cbGrade")
    private ComboBox<GradeDto> cbGrade;
    @Id("cbSubject")
    private ComboBox<SubjectDto> cbSubject;

    /**
     * Creates a new TopicFormDesing.
     */
    public TopicFormDesing() {
        // You can initialise any data required for the connected UI components here.
        tfName.setLabel("TopicName");
        cbSubject.setLabel("TopicSubject");
        cbGrade.setLabel("TopicGrade");
        tfDescription.setLabel("TopicDescription");
    }

    @Override
    public void restore(TopicDto data) {
        tfName.setValue(data.getName());
        tfDescription.setValue(data.getDescription());
        cbSubject.setValue(data.getSubjectDto());
        cbGrade.setValue(data.getGradeDto());

    }

    @Override
    public void visualize() {
        tfName.setReadOnly(true);
        tfDescription.setReadOnly(true);
        cbSubject.setReadOnly(true);
        cbGrade.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        return !tfName.isInvalid() && !tfDescription.isInvalid() && !cbSubject.isInvalid() && !cbGrade.isInvalid();
    }

    @Override
    public TopicDto collectData(TopicDto model) {
        model.setName(tfName.getValue());
        model.setDescription(tfDescription.getValue());
        model.setGradeDto(cbGrade.getValue());
        // model.getSubjectDto(cbSubject.getValue(); verificar klk
        return model;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between TopicFormDesing and topic-form-design.html
     */
    public interface TopicFormDesingModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
