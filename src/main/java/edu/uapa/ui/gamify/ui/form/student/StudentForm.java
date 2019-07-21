package edu.uapa.ui.gamify.ui.form.student;

import edu.uapa.ui.gamify.requests.student.StudentRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.school.student.StudentFormDesign;
import edu.utesa.lib.models.dtos.school.StudentDto;

public class StudentForm extends AbstractSingleForm<StudentFormDesign, StudentDto> {

    public StudentForm() {
        this(new StudentDto(), false, true);
        setNew(true);
    }

    public StudentForm(StudentDto data, boolean onlyView) {
        super(new StudentFormDesign(), data, onlyView, false);
        open();
    }

    private StudentForm(StudentDto data, boolean onlyView, boolean isNew) {
        super(new StudentFormDesign(), data, onlyView, isNew);
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
        return isNew() ? StudentRequests.getInstance().save(getData()) : StudentRequests.getInstance().update(getData());
    }
}
