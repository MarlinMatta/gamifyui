package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the main-form-design template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("main-form-design")
@HtmlImport("src/views/components/main-form-design.html")
public class MainFormDesign extends PolymerTemplate<MainFormDesign.MainFormDesignModel> {

    /**
     * Creates a new MainFormDesign.
     */
    public MainFormDesign() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between MainFormDesign and main-form-design
     */
    public interface MainFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
