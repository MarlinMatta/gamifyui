package edu.uapa.ui.gamify.views.security;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.security.PermissionGroupDto;

/**
 * A Designer generated component for the permission-group-form-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("permission-group-form-design")
@HtmlImport("src/views/security/permission-group-form-design.html")
public class PermissionGroupFormDesign extends PolymerTemplate<PermissionGroupFormDesign.PermissionGroupFormDesignModel> implements FormStructure<PermissionGroupDto> {

    @Id("tfName")
    private TextField tfName;
    @Id("taDescription")
    private TextArea taDescription;

    /**
     * Creates a new PermissionGroupFormDesign.
     */
    public PermissionGroupFormDesign() {
        tfName.setLabel(Captions.NAME);
        tfName.setRequired(true);

        taDescription.setLabel(Captions.DESCRIPTION);
        taDescription.setRequired(true);

        this.getElement().getStyle().set("max-width", "720px");
    }

    @Override
    public void restore(PermissionGroupDto data) {
        tfName.setValue(data.getName());
        taDescription.setValue(data.getDescription());
    }

    @Override
    public void visualize() {
        tfName.setReadOnly(true);
        taDescription.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        return !tfName.isInvalid();
    }

    @Override
    public PermissionGroupDto collectData(PermissionGroupDto model) {
        model.setName(tfName.getValue());
        model.setDescription(taDescription.getValue());
        return model;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between PermissionGroupFormDesign and permission-group-form-design
     */
    public interface PermissionGroupFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
