package edu.uapa.ui.gamify.ui.form.school;

import edu.uapa.ui.gamify.requests.location.CountryRequests;
import edu.uapa.ui.gamify.requests.school.GradeRequests;
import edu.uapa.ui.gamify.requests.school.TeacherRequests;
import edu.uapa.ui.gamify.requests.school.SchoolRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.school.school.TeacherFormDesign;
import edu.utesa.lib.models.dtos.school.TeacherDto;

import java.text.ParseException;

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
        getDesign().fillCountry(CountryRequests.getInstance().getAll());
        getDesign().fillSchool(SchoolRequests.getInstance().getAll());
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
        return isNew() ? TeacherRequests.getInstance().save(getData()) : TeacherRequests.getInstance().update(getData());
    }
}
