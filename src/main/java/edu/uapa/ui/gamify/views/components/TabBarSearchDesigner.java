package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.utils.captions.Captions;

/**
 * A Designer generated component for the tab-bar-search-designer template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("tab-bar-search-designer")
@HtmlImport("src/views/components/tab-bar-search-designer.html")
public class TabBarSearchDesigner extends PolymerTemplate<TabBarSearchDesigner.TabBarSearchDesignerModel> {

    @Id("btnNew")
    private Button btnNew;
    @Id("tfSearch")
    private TextField tfSearch;

    /**
     * Creates a new TabBarSearchDesigner.
     */
    public TabBarSearchDesigner() {
        tfSearch.setPlaceholder(Captions.SEARCH);
        btnNew.setIcon(new Icon(VaadinIcon.PLUS));
        btnNew.setText(Captions.BUTTON_NEW);
    }

    public Button getBtnNew() {
        return btnNew;
    }

    public void setBtnNewAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnNew.addClickListener(clickEvent);
    }

    public void setTfSearchChangeValueAction(HasValue.ValueChangeListener<HasValue.ValueChangeEvent<String>> valueChangeListener) {
        tfSearch.addValueChangeListener(valueChangeListener);
    }

    public String getTfSearchValue() {
        return tfSearch.getValue().isBlank() ? "" : tfSearch.getValue();
    }

    /**
     * This model binds properties between TabBarSearchDesigner and tab-bar-search-designer
     */
    public interface TabBarSearchDesignerModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
