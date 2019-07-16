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
 * A Designer generated component for the pick-action-bar-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("pick-action-bar-design")
@HtmlImport("src/views/components/pick-action-bar-design.html")
public class PickActionBarDesign extends PolymerTemplate<PickActionBarDesign.PickActionBarDesignModel> {

    @Id("btnSelect")
    private Button btnSelect;
    @Id("btnExit")
    private Button btnExit;

    /**
     * Creates a new PickActionBarDesign.
     */
    public PickActionBarDesign() {
        btnSelect.setText(Captions.BUTTON_SELECT);
        btnExit.setText(Captions.BUTTON_EXIT);
        getElement().getStyle().set("width", "100%");
    }

    public void setSelectAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnSelect.addClickListener(clickEvent);
    }

    public void setExitAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnExit.addClickListener(clickEvent);
    }

    /**
     * This model binds properties between PickActionBarDesign and pick-action-bar-design
     */
    public interface PickActionBarDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
