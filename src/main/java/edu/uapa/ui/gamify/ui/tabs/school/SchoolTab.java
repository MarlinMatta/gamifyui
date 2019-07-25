package edu.uapa.ui.gamify.ui.tabs.school;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.school.SchoolRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.school.SchoolForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.SchoolDto;

import java.util.List;

public class SchoolTab extends AbstractTabWithGrid<SchoolDto> {

    public SchoolTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(SchoolDto::getName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(SchoolDto::getDistrict).setHeader(Captions.GRID_COLUMN_DISTRICT);
        getGrid().addColumn(SchoolDto::getAddress).setHeader(Captions.GRID_COLUMN_ADDRESS);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return SchoolRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<SchoolDto> getData(Long page, Long size, String searchValue) {
        return SchoolRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new SchoolForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(SchoolDto data) {
        new SchoolForm(data, true);
    }

    @Override
    protected void edit(SchoolDto data) {
        new SchoolForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        SchoolRequests.getInstance().delete(id);
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
