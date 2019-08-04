package edu.uapa.ui.gamify.ui.form.gamifies;

import edu.uapa.ui.gamify.requests.gamifies.TopicRequests;
import edu.uapa.ui.gamify.requests.school.SubjectRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.school.school.TopicFormDesign;
import edu.utesa.lib.models.dtos.school.TopicDto;

public class TopicForm extends AbstractSingleForm<TopicFormDesign, TopicDto> {

    public TopicForm() {
        this(new TopicDto(), false, true);
        setNew(true);
    }

    public TopicForm(TopicDto data, boolean onlyView) {
        super(new TopicFormDesign(), data, onlyView, false);
        open();
    }

    private TopicForm(TopicDto data, boolean onlyView, boolean isNew) {
        super(new TopicFormDesign(), data, onlyView, isNew);
        open();
    }

    @Override
    protected void setLanguage() {
        getDesign().fillSubject(SubjectRequests.getInstance().getAll());
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
        return isNew() ? TopicRequests.getInstance().save(getData()) : TopicRequests.getInstance().update(getData());
    }
}
