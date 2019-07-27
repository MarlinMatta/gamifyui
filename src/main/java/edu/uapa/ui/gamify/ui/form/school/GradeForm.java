package edu.uapa.ui.gamify.ui.form.school;

import edu.uapa.ui.gamify.requests.school.GradeRequests;
import edu.uapa.ui.gamify.requests.school.SchoolRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.school.school.GradeFormDesign;
import edu.utesa.lib.models.dtos.school.GradeDto;

public class GradeForm extends AbstractSingleForm<GradeFormDesign, GradeDto> {

    public GradeForm() {
        this(new GradeDto(), false, true);
        setNew(true);
    }

    public GradeForm(GradeDto data, boolean onlyView) {
        super(new GradeFormDesign(), data, onlyView, false);
        open();
    }

    private GradeForm(GradeDto data, boolean onlyView, boolean isNew) {
        super(new GradeFormDesign(), data, onlyView, isNew);
        open();
    }

    @Override
    protected void setLanguage() {
        getDesign().fillSchool(SchoolRequests.getInstance().getAll());
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
        return isNew() ? GradeRequests.getInstance().save(getData()) : GradeRequests.getInstance().update(getData());
    }
}
