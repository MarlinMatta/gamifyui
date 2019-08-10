package edu.uapa.ui.gamify.ui.tabs.school;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.school.GradeRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.school.GradeForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.GradeDto;

import java.util.List;

public class GradeTab extends AbstractTabWithGrid<GradeDto> {

    public GradeTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(GradeDto::getName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(GradeDto::getDescription).setHeader(Captions.GRID_COLUMN_DESCRIPTION);
        getGrid().addColumn(GradeDto::theSchoolName).setHeader(Captions.GRID_COLUMN_SCHOOL);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return GradeRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<GradeDto> getData(Long page, Long size, String searchValue) {
        return GradeRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new GradeForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(GradeDto data) {
        new GradeForm(data, true);
    }

    @Override
    protected void edit(GradeDto data) {
        new GradeForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        GradeRequests.getInstance().delete(id);
        refresh();
    }

    @Override
    protected void cantAdd(Component component) {
        component.setVisible(!getLoginManager().hasPermission(0));
    }

    @Override
    protected void cantView(Component component) {
        component.setVisible(true);
    }

    @Override
    protected void cantEdit(Component component) {
        component.setVisible(!getLoginManager().hasPermission(0));
    }

    @Override
    protected void cantDelete(Component component) {
        component.setVisible(!getLoginManager().hasPermission(0));
    }
}
