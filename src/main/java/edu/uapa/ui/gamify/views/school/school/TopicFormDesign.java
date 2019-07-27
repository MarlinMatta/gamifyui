package edu.uapa.ui.gamify.views.school.school;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.SubjectDto;
import edu.utesa.lib.models.dtos.school.TopicDto;

import java.util.List;

/**
 * A Designer generated component for the topic-form-design.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("topic-form-design")
@HtmlImport("src/views/school/topic-form-design.html")
public class TopicFormDesign extends PolymerTemplate<TopicFormDesign.TopicFormDesignModel> implements FormStructure<TopicDto> {

    @Id("cbSubject")
    private ComboBox<SubjectDto> cbSubject;
    @Id("tfName")
    private TextField tfName;
    @Id("tfDescription")
    private TextField tfDescription;

    private SubjectDto subject;

    /**
     * Creates a new TopicFormDesing.
     */
    public TopicFormDesign() {
        // You can initialise any data required for the connected UI components here.
        tfName.setLabel(Captions.NAME);
        cbSubject.setLabel(Captions.SUBJECT);
        tfDescription.setLabel(Captions.DESCRIPTION);

        subject = new SubjectDto();
    }

    public void fillSubject(List<SubjectDto> items) {
        cbSubject.setItemLabelGenerator(SubjectDto::getName);
        cbSubject.setItems(items);
    }

    @Override
    public void restore(TopicDto data) {
        subject = data.getSubjectDto();

        cbSubject.setValue(data.getSubjectDto());
        tfName.setValue(data.getName());
        tfDescription.setValue(data.getDescription() == null ? "" : data.getDescription());
    }

    @Override
    public void visualize() {
        tfName.setReadOnly(true);
        tfDescription.setReadOnly(true);
        cbSubject.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        if (tfName.isInvalid())
            return false;
        if (cbSubject.isInvalid())
            return false;
        if (tfDescription.isInvalid())
            return false;
        return true;
    }

    @Override
    public TopicDto collectData(TopicDto model) {
        subject = cbSubject.getValue();

        model.setName(tfName.getValue());
        model.setDescription(tfDescription.getValue());
        model.setSubjectDto(subject);
        return model;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between TopicFormDesing and topic-form-design.html
     */
    public interface TopicFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
