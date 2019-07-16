package edu.uapa.ui.gamify.views.school;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated component for the subject-form-design.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("subject-form-design")
@HtmlImport("src/views/school/subject-form-design.html")
public class SubjectFormDesign extends PolymerTemplate<SubjectFormDesign.SubjectFormDesignModel> {

    @Id("tfName")
    private TextField tfName;
    @Id("tfDescription")
    private TextField tfDescription;

    /**
     * Creates a new SubjectFormDesign.
     */
    public SubjectFormDesign() {
        // You can initialise any data required for the connected UI components here.
        tfName.setLabel("SubjectName");
        tfDescription.setLabel("SubjectDescription");
    }

    /**
     * This model binds properties between SubjectFormDesign and subject-form-design.html
     */
    public interface SubjectFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
