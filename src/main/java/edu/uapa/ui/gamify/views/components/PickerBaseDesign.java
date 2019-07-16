package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;

import java.util.List;
import java.util.Set;

/**
 * A Designer generated component for the picker-base-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("picker-base-design")
@HtmlImport("src/views/components/picker-base-design.html")
public class PickerBaseDesign<T> extends PolymerTemplate<PickerBaseDesign.PickerBaseDesignModel> {

    @Id("tfSearch")
    private TextField tfSearch;
    @Id("grid")
    private Grid<T> grid;

    /**
     * Creates a new PickerBaseDesign.
     */
    public PickerBaseDesign() {
        tfSearch.setPlaceholder(Captions.SEARCH);
        grid.setPageSize(Tools.DEFAULT_ITEMS_PER_PAGE_VALUE.intValue());
        getElement().getStyle().set("width", "100%");
    }

    public void setTfSearchChangeValueAction(HasValue.ValueChangeListener<HasValue.ValueChangeEvent<String>> valueChangeListener) {
        tfSearch.addValueChangeListener(valueChangeListener);
    }

    public String getTfSearchValue() {
        return tfSearch.getValue().isBlank() ? "" : tfSearch.getValue();
    }

    public void setTheme(GridVariant... variants) {
        grid.addThemeVariants(variants);
    }

    public void setData(List<T> items) {
        grid.setItems(items);
    }

    public void setDataSelected(Set<T> items) {
        grid.asMultiSelect().setValue(items);
    }

    public Set<T> getDataSelected() {
        return grid.getSelectedItems();
    }

    public void setMultiSelectionMode(boolean multi) {
        if (multi)
            grid.setSelectionMode(Grid.SelectionMode.MULTI);
        else
            grid.setSelectionMode(Grid.SelectionMode.SINGLE);
    }

    public Grid<T> getGrid() {
        return grid;
    }

    /**
     * This model binds properties between PickerBaseDesign and picker-base-design
     */
    public interface PickerBaseDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
