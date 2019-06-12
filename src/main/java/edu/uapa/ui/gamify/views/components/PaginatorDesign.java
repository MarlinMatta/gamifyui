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
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.utils.Tools;

/**
 * A Designer generated component for the paginator-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("paginator-design")
@HtmlImport("src/views/components/paginator-design.html")
public class PaginatorDesign extends PolymerTemplate<PaginatorDesign.PaginatorDesignModel> {

    @Id("btnFirst")
    private Button btnFirst;
    @Id("btnPrevious")
    private Button btnPrevious;
    @Id("tfPage")
    private TextField tfPage;
    @Id("btnNext")
    private Button btnNext;
    @Id("btnLast")
    private Button btnLast;
    @Id("cbItemsPage")
    private Select<Long> cbItemsPage;

    public PaginatorDesign() {
        cbItemsPage.setWidth("70px");
        cbItemsPage.setItems(Tools.ITEMS_PER_PAGE);
        cbItemsPage.setValue(Tools.DEFAULT_ITEMS_PER_PAGE_VALUE);

        btnFirst.setIcon(new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT));
        btnPrevious.setIcon(new Icon(VaadinIcon.ANGLE_LEFT));

        tfPage.setWidth("70px");
        tfPage.setReadOnly(true);
        tfPage.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        btnNext.setIcon(new Icon(VaadinIcon.ANGLE_RIGHT));
        btnLast.setIcon(new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
    }

    public void setCbItemsPage(HasValue.ValueChangeListener<HasValue.ValueChangeEvent<Long>> valueChangeListener) {
        cbItemsPage.addValueChangeListener(valueChangeListener);
    }

    public void setBtnFirstAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnFirst.addClickListener(clickEvent);
    }

    public void setBtnPrevious(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnPrevious.addClickListener(clickEvent);
    }

    public void setTfPageValue(String value) {
        tfPage.setValue(value);
    }

    public void setBtnNext(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnNext.addClickListener(clickEvent);
    }

    public void setBtnLast(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnLast.addClickListener(clickEvent);
    }

    public void enableBtnFirst() {
        btnFirst.setEnabled(true);
    }

    public void enableBtnPrevious() {
        btnPrevious.setEnabled(true);
    }

    public void enableBtnNext() {
        btnNext.setEnabled(true);
    }

    public void enableBtnLast() {
        btnLast.setEnabled(true);
    }

    public void disabledBtnFirst() {
        btnFirst.setEnabled(false);
    }

    public void disabledBtnPrevious() {
        btnPrevious.setEnabled(false);
    }

    public void disabledBtnNext() {
        btnNext.setEnabled(false);
    }

    public void disabledBtnLast() {
        btnLast.setEnabled(false);
    }

    public interface PaginatorDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
