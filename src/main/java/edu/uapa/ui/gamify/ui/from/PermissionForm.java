package edu.uapa.ui.gamify.ui.from;

import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.uapa.ui.gamify.models.interfaces.FormEstruture;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.security.PermissionFormDesign;

public class PermissionForm extends AbstractSingleForm<PermissionFormDesign, PermissionDto> implements FormEstruture<PermissionDto> {

    public PermissionForm() {
        super(new PermissionFormDesign());
    }

    @Override
    protected void setLanguage() {
    }

    @Override
    protected void setAction() {

    }

    @Override
    public void restore() {

    }

    @Override
    public void visualize() {

    }

    @Override
    public boolean validComponent() {
        return false;
    }

    @Override
    public void collectData() {

    }

    @Override
    public void security() {

    }
}
