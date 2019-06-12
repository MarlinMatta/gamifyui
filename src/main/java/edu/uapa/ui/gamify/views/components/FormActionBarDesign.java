package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.utils.captions.Captions;

/**
 * A Designer generated component for the form-action-bar-design template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("form-action-bar-design")
@HtmlImport("src/views/components/form-action-bar-design.html")
public class FormActionBarDesign extends PolymerTemplate<FormActionBarDesign.FormActionBarDesignModel> {

    @Id("btnSave")
    private Button btnSave;
    @Id("btnCancel")
    private Button btnCancel;

    /**
     * Creates a new FormActionBarDesign.
     */
    public FormActionBarDesign() {
        btnSave.setText(Captions.BUTTON_SAVE);
        btnCancel.setText(Captions.BUTTON_CANCEL);
    }

    public void setBtnSaveAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnSave.addClickListener(clickEvent);
    }

    public void setBtnCancelAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnCancel.addClickListener(clickEvent);
    }

    /**
     * This model binds properties between FormActionBarDesign and form-action-bar-design
     */
    public interface FormActionBarDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
