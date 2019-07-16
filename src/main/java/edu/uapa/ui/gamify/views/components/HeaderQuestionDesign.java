package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated component for the header-question-design.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("header-question-design")
@HtmlImport("src/views/components/header-question-design.html")
public class HeaderQuestionDesign extends PolymerTemplate<HeaderQuestionDesign.HeaderQuestionDesignModel> {

    /**
     * Creates a new HeaderQuestionDesign.
     */
    public HeaderQuestionDesign() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between HeaderQuestionDesign and header-question-design.html
     */
    public interface HeaderQuestionDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
