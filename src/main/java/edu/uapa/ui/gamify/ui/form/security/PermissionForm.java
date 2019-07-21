package edu.uapa.ui.gamify.ui.form.security;

import edu.uapa.ui.gamify.requests.security.PermissionRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.security.PermissionFormDesign;
import edu.utesa.lib.models.dtos.security.PermissionDto;

public class PermissionForm extends AbstractSingleForm<PermissionFormDesign, PermissionDto> {

    public PermissionForm() {
        this(new PermissionDto(), false, true);
        setNew(true);
    }

    public PermissionForm(PermissionDto data, boolean onlyView) {
        super(new PermissionFormDesign(), data, onlyView, false);
        open();
    }

    private PermissionForm(PermissionDto data, boolean onlyView, boolean isNew) {
        super(new PermissionFormDesign(), data, onlyView, isNew);
        open();
    }

    @Override
    protected void setLanguage() {
    }

    @Override
    protected void visualize() {
        getDesign().visualize();
    }

    @Override
    protected void restore() {
        getDesign().restore(getData());
    }

    @Override
    protected boolean validField() {
        return getDesign().validField();
    }

    @Override
    protected void collect() {
        setData(getDesign().collectData(getData()));
    }

    @Override
    protected boolean persistence() {
        return isNew() ? PermissionRequests.getInstance().save(getData()) : PermissionRequests.getInstance().update(getData());
    }
}
