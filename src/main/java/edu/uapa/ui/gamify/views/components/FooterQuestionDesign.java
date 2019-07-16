package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated component for the footer-question-design.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("footer-question-design")
@HtmlImport("src/views/components/footer-question-design.html")
public class FooterQuestionDesign extends PolymerTemplate<FooterQuestionDesign.FooterQuestionDesignModel> {

    /**
     * Creates a new FooterQuestionDesign.
     */
    public FooterQuestionDesign() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between FooterQuestionDesign and footer-question-design.html
     */
    public interface FooterQuestionDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
