package edu.uapa.ui.gamify.ui.tabs.gamifies;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.gamifies.TopicRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.gamifies.TopicForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.TopicDto;

import java.util.List;

public class TopicTab extends AbstractTabWithGrid<TopicDto> {

    public TopicTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(TopicDto::getName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(TopicDto::getDescription).setHeader(Captions.GRID_COLUMN_DESCRIPTION);
        getGrid().addColumn(TopicDto::theSubjectName).setHeader(Captions.GRID_COLUMN_SUBJECT);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return TopicRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<TopicDto> getData(Long page, Long size, String searchValue) {
        return TopicRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new TopicForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(TopicDto data) {
        new TopicForm(data, true);
    }

    @Override
    protected void edit(TopicDto data) {
        new TopicForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        TopicRequests.getInstance().delete(id);
        refresh();
    }

    @Override
    protected void cantAdd(Component component) {
        component.setVisible(true);
    }

    @Override
    protected void cantView(Component component) {
        component.setVisible(true);
    }

    @Override
    protected void cantEdit(Component component) {
        component.setVisible(true);
    }

    @Override
    protected void cantDelete(Component component) {
        component.setVisible(false);
    }
}
