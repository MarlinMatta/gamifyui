package edu.uapa.ui.gamify.ui.form.gamifies;

import edu.uapa.ui.gamify.requests.gamifies.ExamRequests;
import edu.uapa.ui.gamify.requests.gamifies.TopicRequests;
import edu.uapa.ui.gamify.requests.school.TeacherRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.gamifies.ExamFormDesign;
import edu.utesa.lib.models.dtos.school.ExamDto;

public class ExamForm extends AbstractSingleForm<ExamFormDesign, ExamDto> {

    public ExamForm() {
        this(new ExamDto(), false, true);
        setNew(true);
    }

    public ExamForm(ExamDto data, boolean onlyView) {
        super(new ExamFormDesign(), data, onlyView, false);
        open();
    }

    private ExamForm(ExamDto data, boolean onlyView, boolean isNew) {
        super(new ExamFormDesign(), data, onlyView, isNew);
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
        return isNew() ? ExamRequests.getInstance().save(getData()) : ExamRequests.getInstance().update(getData());
    }
}
