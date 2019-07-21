package edu.uapa.ui.gamify.ui.tabs.school;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.school.SubjectRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.school.SubjectForm;
import edu.uapa.ui.gamify.ui.form.school.SubjectForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.SubjectDto;

import java.util.List;

public class SubjectTab extends AbstractTabWithGrid<SubjectDto> {

    public SubjectTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(SubjectDto::getName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(SubjectDto::getDescription).setHeader(Captions.GRID_COLUMN_DESCRIPTION);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return SubjectRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<SubjectDto> getData(Long page, Long size, String searchValue) {
        return SubjectRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new SubjectForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(SubjectDto data) {
        new SubjectForm(data, true);
    }

    @Override
    protected void edit(SubjectDto data) {
        new SubjectForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        SubjectRequests.getInstance().delete(id);
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
