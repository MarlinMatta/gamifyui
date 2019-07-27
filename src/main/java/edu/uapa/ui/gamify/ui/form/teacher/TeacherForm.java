package edu.uapa.ui.gamify.ui.form.teacher;

import edu.uapa.ui.gamify.requests.teacher.TeacherRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.school.school.TeacherFormDesign;
import edu.utesa.lib.models.dtos.school.TeacherDto;

public class TeacherForm extends AbstractSingleForm<TeacherFormDesign, TeacherDto> {

    public TeacherForm() {
        this(new TeacherDto(), false, true);
        setNew(true);
    }

    public TeacherForm(TeacherDto data, boolean onlyView) {
        super(new TeacherFormDesign(), data, onlyView, false);
        open();
    }

    private TeacherForm(TeacherDto data, boolean onlyView, boolean isNew) {
        super(new TeacherFormDesign(), data, onlyView, isNew);
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
        return isNew() ? TeacherRequests.getInstance().save(getData()) : TeacherRequests.getInstance().update(getData());
    }
}
