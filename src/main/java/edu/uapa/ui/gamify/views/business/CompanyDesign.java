package edu.uapa.ui.gamify.views.business;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated components for the ParameterRoute-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("ParameterRoute-design")
@HtmlImport("src/views/ParameterRoute-design.html")
public class CompanyDesign extends PolymerTemplate<CompanyDesign.CompanyDesignModel> {

    /**
     * Creates a new CompanyDesign.
     */
    public CompanyDesign() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between CompanyDesign and ParameterRoute-design
     */
    public interface CompanyDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
