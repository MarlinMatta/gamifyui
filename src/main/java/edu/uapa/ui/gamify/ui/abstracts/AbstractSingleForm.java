package edu.uapa.ui.gamify.ui.abstracts;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractDialog;

public abstract class AbstractSingleForm<C extends Component, D> extends AbstractDialog {

    private C design;
    private D data;

    protected AbstractSingleForm(C design) {
        this.design = design;
        initialized();
        setLanguage();
        setAction();
    }

    private void initialized() {
        add(design);
    }

    protected abstract void setLanguage();

    protected abstract void setAction();

    public C getDesign() {
        return design;
    }

    public D getData() {
        return data;
    }
}