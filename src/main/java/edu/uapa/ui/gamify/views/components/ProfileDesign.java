package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the profile-design template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("profile-design")
@HtmlImport("src/views/components/profile-design.html")
public class ProfileDesign extends PolymerTemplate<ProfileDesign.ProfileDesignModel> {

    /**
     * Creates a new ProfileDesign.
     */
    public ProfileDesign() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between ProfileDesign and profile-design
     */
    public interface ProfileDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
