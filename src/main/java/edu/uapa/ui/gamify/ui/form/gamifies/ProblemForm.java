package edu.uapa.ui.gamify.ui.form.gamifies;

import edu.uapa.ui.gamify.requests.gamifies.ProblemRequests;
import edu.uapa.ui.gamify.requests.school.TeacherRequests;
import edu.uapa.ui.gamify.requests.gamifies.TopicRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.gamifies.ProblemFormDesign;
import edu.utesa.lib.models.dtos.school.ProblemDto;

public class ProblemForm extends AbstractSingleForm<ProblemFormDesign, ProblemDto> {

    public ProblemForm() {
        this(new ProblemDto(), false, true);
        setNew(true);
    }

    public ProblemForm(ProblemDto data, boolean onlyView) {
        super(new ProblemFormDesign(), data, onlyView, false);
        open();
    }

    private ProblemForm(ProblemDto data, boolean onlyView, boolean isNew) {
        super(new ProblemFormDesign(), data, onlyView, isNew);
        open();
    }

    @Override
    protected void setLanguage() {
        getDesign().fillTeacher(TeacherRequests.getInstance().getAll());
        getDesign().fillTopic(TopicRequests.getInstance().getAll());
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
        return isNew() ? ProblemRequests.getInstance().save(getData()) : ProblemRequests.getInstance().update(getData());
    }
}
