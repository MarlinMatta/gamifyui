package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.utils.captions.Captions;

import java.util.Set;

/**
 * A Designer generated component for the form-tab-with-grid template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("form-tab-with-grid")
@HtmlImport("src/views/components/form-tab-with-grid.html")
public class FormTabWithGrid<T> extends PolymerTemplate<FormTabWithGrid.FormTabWithGridModel> {

    @Id("tfSearch")
    private TextField tfSearch;
    @Id("btnAdd")
    private Button btnAdd;
    @Id("btnDelete")
    private Button btnDelete;
    @Id("grid")
    private Grid<T> grid;

    /**
     * Creates a new FormTabWithGrid.
     */
    public FormTabWithGrid() {
        tfSearch.setPlaceholder(Captions.SEARCH);
        btnAdd.setIcon(new Icon(VaadinIcon.PLUS));
        btnDelete.setIcon(new Icon(VaadinIcon.TRASH));

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
    }

    public void setTfSearchChangeValueAction(HasValue.ValueChangeListener<HasValue.ValueChangeEvent<String>> valueChangeListener) {
        tfSearch.addValueChangeListener(valueChangeListener);
    }

    public void setAddAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnAdd.addClickListener(clickEvent);
    }

    public void setDeleteAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnDelete.addClickListener(clickEvent);
    }

    public void setTheme(GridVariant... variants) {
        grid.addThemeVariants(variants);
    }

    public void setData(Set<T> items) {
        grid.setItems(items);
    }

    public void setDataSelected(Set<T> items) {
        grid.asMultiSelect().setValue(items);
    }

    public Set<T> getData() {
        return grid.getSelectedItems();
    }

    public Grid<T> getGrid() {
        return grid;
    }

    /**
     * This model binds properties between FormTabWithGrid and form-tab-with-grid
     */
    public interface FormTabWithGridModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
