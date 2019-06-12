package edu.uapa.ui.gamify.views.security;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.utils.captions.Captions;

/**
 * A Designer generated component for the permission-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("permission-design")
@HtmlImport("src/views/security/permission-form-design.html")
public class PermissionFormDesign extends PolymerTemplate<PermissionFormDesign.PermissionDesignModel> {

//    @Id("lbCode")
//    private Label lbCode;
//    @Id("tfCode")
//    private TextField tfCode;
//    @Id("lbName")
//    private Label lbName;
//    @Id("tfName")
//    private TextField tfName;
//    @Id("lbDescription")
//    private Label lbDescription;
//    @Id("taDescription")
//    private TextArea taDescription;

    /**
     * Creates a new PermissionFormDesign.
     */
    public PermissionFormDesign() {
//        lbCode.setText(Captions.BUTTON_VIEW);
//        lbName.setText(Captions.NAME);
//        lbDescription.setText(Captions.DESCRIPTION);
    }

//    public TextField getTfCode() {
//        return tfCode;
//    }
//
//    public void setTfCode(TextField tfCode) {
//        this.tfCode = tfCode;
//    }
//
//    public TextField getTfName() {
//        return tfName;
//    }
//
//    public void setTfName(TextField tfName) {
//        this.tfName = tfName;
//    }
//
//    public TextArea getTaDescription() {
//        return taDescription;
//    }
//
//    public void setTaDescription(TextArea taDescription) {
//        this.taDescription = taDescription;
//    }

    /**
     * This model binds properties between PermissionFormDesign and permission-design
     */
    public interface PermissionDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
