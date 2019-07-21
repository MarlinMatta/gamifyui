package edu.uapa.ui.gamify.ui.form.security;

import com.vaadin.flow.component.notification.Notification;
import edu.uapa.ui.gamify.requests.security.PermissionRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.security.PermissionGroupFormDesign;
import edu.utesa.lib.models.dtos.security.PermissionGroupDto;

public class PermissionGroupForm extends AbstractSingleForm<PermissionGroupFormDesign, PermissionGroupDto> {

    public PermissionGroupForm() {
        this(new PermissionGroupDto(), false, true);
        setNew(true);
    }

    public PermissionGroupForm(PermissionGroupDto data, boolean onlyView) {
        super(new PermissionGroupFormDesign(), data, onlyView, false);
        open();
    }

    private PermissionGroupForm(PermissionGroupDto data, boolean onlyView, boolean isNew) {
        super(new PermissionGroupFormDesign(), data, onlyView, isNew);
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
        if (getDesign().validField())
            return true;
        else
            Notification.show("Campos incompleto");
        return false;
    }

    @Override
    protected void collect() {
        setData(getDesign().collectData(getData()));
    }

    @Override
    protected boolean persistence() {
        return isNew() ? PermissionRequests.getInstance().saveGroup(getData()) : PermissionRequests.getInstance().updateGroup(getData());
    }
}
