package edu.uapa.ui.gamify.ui.form.school;

import edu.uapa.ui.gamify.requests.school.GradeRequests;
import edu.uapa.ui.gamify.requests.school.SubjectRequests;
import edu.uapa.ui.gamify.requests.school.TeacherRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.school.school.SubjectFormDesign;
import edu.utesa.lib.models.dtos.school.SubjectDto;

public class SubjectForm extends AbstractSingleForm<SubjectFormDesign, SubjectDto> {

    public SubjectForm() {
        this(new SubjectDto(), false, true);
        setNew(true);
    }

    public SubjectForm(SubjectDto data, boolean onlyView) {
        super(new SubjectFormDesign(), data, onlyView, false);
        open();
    }

    private SubjectForm(SubjectDto data, boolean onlyView, boolean isNew) {
        super(new SubjectFormDesign(), data, onlyView, isNew);
        open();
    }

    @Override
    protected void setLanguage() {
        getDesign().fillTeacher(TeacherRequests.getInstance().getAll());
        getDesign().fillGrade(GradeRequests.getInstance().getAll());
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
        return isNew() ? SubjectRequests.getInstance().save(getData()) : SubjectRequests.getInstance().update(getData());
    }
}
