package edu.uapa.ui.gamify.views.security;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.security.PermissionDto;

/**
 * A Designer generated component for the permission-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("permission-design")
@HtmlImport("src/views/security/permission-form-design.html")
public class PermissionFormDesign extends PolymerTemplate<PermissionFormDesign.PermissionDesignModel> implements FormStructure<PermissionDto> {
    @Id("tfCode")
    private TextField tfCode;
    @Id("tfName")
    private TextField tfName;
    @Id("taDescription")
    private TextArea taDescription;

    /**
     * Creates a new PermissionFormDesign.
     */
    public PermissionFormDesign() {
        tfCode.setLabel(Captions.CODE);
        tfName.setLabel(Captions.NAME);
        taDescription.setLabel(Captions.DESCRIPTION);

        tfCode.setRequired(true);
        tfName.setRequired(true);

        this.getElement().getStyle().set("max-width", "720px");
    }

    @Override
    public void restore(PermissionDto dto) {
        tfCode.setValue(dto.getCode() + "");
        tfName.setValue(dto.getName());
        taDescription.setValue(dto.getDescription());
    }

    @Override
    public void visualize() {
        tfCode.setReadOnly(true);
        tfName.setReadOnly(true);
        taDescription.setReadOnly(true);
    }

    public boolean validField() {
        if (tfCode.isInvalid()) {
            Notification.show("Incomplete code field");
            return false;
        } else if (tfName.isInvalid()) {
            Notification.show("Incomplete name field");
            return false;
        }
        return true;
    }

    @Override
    public PermissionDto collectData(PermissionDto model) {
        model.setCode(Integer.parseInt(tfCode.getValue()));
        model.setName(tfName.getValue());
        model.setDescription(taDescription.getValue());
        return model;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between PermissionFormDesign and permission-design
     */
    public interface PermissionDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
