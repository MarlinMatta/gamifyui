package edu.uapa.ui.gamify.ui.from;

import edu.uapa.ui.gamify.requests.security.ParameterRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.security.ParameterFormDesign;
import edu.utesa.lib.models.dtos.security.ParamDto;

public class ParameterForm extends AbstractSingleForm<ParameterFormDesign, ParamDto> {

    public ParameterForm() {
        this(new ParamDto(), false, true);
        setNew(true);
    }

    public ParameterForm(ParamDto data, boolean onlyView) {
        super(new ParameterFormDesign(), data, onlyView, false);
        open();
    }

    private ParameterForm(ParamDto data, boolean onlyView, boolean isNew) {
        super(new ParameterFormDesign(), data, onlyView, isNew);
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
        return isNew() ? ParameterRequests.getInstance().save(getData()) : ParameterRequests.getInstance().update(getData());
    }
}
