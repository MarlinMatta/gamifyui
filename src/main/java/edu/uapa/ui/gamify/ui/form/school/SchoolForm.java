package edu.uapa.ui.gamify.ui.form.school;

import edu.uapa.ui.gamify.requests.location.CountryRequests;
import edu.uapa.ui.gamify.requests.school.SchoolRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.school.school.SchoolFormDesign;
import edu.utesa.lib.models.dtos.school.SchoolDto;

public class SchoolForm extends AbstractSingleForm<SchoolFormDesign, SchoolDto> {

    public SchoolForm() {
        this(new SchoolDto(), false, true);
        setNew(true);
    }

    public SchoolForm(SchoolDto data, boolean onlyView) {
        super(new SchoolFormDesign(), data, onlyView, false);
        open();
    }

    private SchoolForm(SchoolDto data, boolean onlyView, boolean isNew) {
        super(new SchoolFormDesign(), data, onlyView, isNew);
        open();
    }

    @Override
    protected void setLanguage() {
        getDesign().fillCountry(CountryRequests.getInstance().getAll());
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
        return isNew() ? SchoolRequests.getInstance().save(getData()) : SchoolRequests.getInstance().update(getData());
    }
}
