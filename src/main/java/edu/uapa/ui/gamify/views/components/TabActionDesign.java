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
 * A Designer generated component for the tab-action-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("tab-action-design")
@HtmlImport("src/views/components/tab-action-design.html")
public class TabActionDesign extends PolymerTemplate<TabActionDesign.TabActionDesignModel> {

    @Id("btnView")
    private Button btnView;
    @Id("btnEdit")
    private Button btnEdit;
    @Id("btnDelete")
    private Button btnDelete;

    /**
     * Creates a new TabActionDesign.
     */
    public TabActionDesign() {
        btnView.setText(Captions.BUTTON_VIEW);
        btnEdit.setText(Captions.BUTTON_EDIT);
        btnDelete.setText(Captions.BUTTON_DELETE);
    }

    public void setViewAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnView.addClickListener(clickEvent);
    }

    public void setEditAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnEdit.addClickListener(clickEvent);
    }

    public void setDeleteAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnDelete.addClickListener(clickEvent);
    }

    public Button getBtnView() {
        return btnView;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    /**
     * This model binds properties between TabActionDesign and tab-action-design
     */
    public interface TabActionDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
