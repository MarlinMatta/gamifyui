package edu.uapa.ui.gamify.ui.tabs.gamifies;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.gamifies.ProblemRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.gamifies.ProblemForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.ProblemDto;

import java.util.List;

public class ProblemTab extends AbstractTabWithGrid<ProblemDto> {

    public ProblemTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(ProblemDto::theFullName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(ProblemDto::theTopicName).setHeader(Captions.GRID_COLUMN_TOPIC);
        getGrid().addColumn(ProblemDto::getQuestion).setHeader(Captions.GRID_COLUMN_QUESTION);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return ProblemRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<ProblemDto> getData(Long page, Long size, String searchValue) {
        return ProblemRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new ProblemForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(ProblemDto data) {
        new ProblemForm(data, true);
    }

    @Override
    protected void edit(ProblemDto data) {
        new ProblemForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        ProblemRequests.getInstance().delete(id);
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
