package edu.uapa.ui.gamify.ui.abstracts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.tabs.Tabs;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractDialog;
import edu.uapa.ui.gamify.ui.components.TabsManager;
import edu.uapa.ui.gamify.views.components.FormActionBarDesign;

public abstract class AbstractSingleForm<C extends Component, D> extends AbstractDialog {

    @Id("form-action-bar-design")
    private FormActionBarDesign action = new FormActionBarDesign();
    private TabsManager tabsManager;
    private C design;
    private D data;
    private boolean onlyView;
    private boolean isNew;
    private boolean withTab = false;
    private VerticalLayout mainLayout = new VerticalLayout();

    protected AbstractSingleForm(C design, D data, boolean onlyView, boolean isNew) {
        this.design = design;
        this.data = data;
        this.onlyView = onlyView;
        this.isNew = isNew;
        setLanguage();
        initialized();
        setAction();
        if (onlyView) {
            visualize();
            action.disabledBtnSave();
        }
    }

    private void initialized() {
        mainLayout.add(design);
        mainLayout.add(new Hr());
        mainLayout.add(action);
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);
        mainLayout.setPadding(false);

        action.getElement().getStyle().set("width", "100%");
        if (withTab) {
            tabsManager = new TabsManager(new Tabs());
            add(tabsManager);
            tabsManager.addTab("Info", mainLayout, false);
        } else {
            add(mainLayout);
        }
        if (!isNew) {
            restore();
        }
    }

    private void setAction() {
        action.setBtnCancelAction(event -> close());
        action.setBtnSaveAction(event -> save());
    }

    protected abstract void setLanguage();

    private void save() {
        if (validField()) {
            collect();
            if (persistence()) {
                Notification.show("Operacion exitosa", 5000, Notification.Position.BOTTOM_END);
                close();
            } else {
                Notification.show("Error en la comunciacion durante la operacion.", 5000, Notification.Position.MIDDLE);
            }
        } else {
            Notification.show("Campos imcompleto...", 5000, Notification.Position.MIDDLE);
        }
    }

    protected abstract void visualize();

    protected abstract void restore();

    protected abstract boolean validField();

    protected abstract void collect();

    protected abstract boolean persistence();

    public C getDesign() {
        return design;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public boolean isOnlyView() {
        return onlyView;
    }

    public void setOnlyView(boolean onlyView) {
        this.onlyView = onlyView;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public void setWithTab(boolean withTab) {
        this.withTab = withTab;
    }
}