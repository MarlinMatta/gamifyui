package edu.uapa.ui.gamify.views.school.configuration;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the problem-design template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("problem-design")
@HtmlImport("src/views/school/configuration/problem-design.html")
public class ProblemDesign extends PolymerTemplate<ProblemDesign.ProblemDesignModel> {

    /**
     * Creates a new ProblemDesign.
     */
    public ProblemDesign() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between ProblemDesign and problem-design
     */
    public interface ProblemDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
