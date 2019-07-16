package edu.uapa.ui.gamify.ui.tabs.security;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.security.PermissionRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.from.PermissionForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.security.PermissionDto;

import java.util.List;

public class PermissionTab extends AbstractTabWithGrid<PermissionDto> {

    public PermissionTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(PermissionDto::getCode).setHeader(Captions.GRID_COLUMN_CODE);
        getGrid().addColumn(PermissionDto::getName).setHeader(Captions.GRID_COLUMN_NAME);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return PermissionRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<PermissionDto> getData(Long page, Long size, String searchValue) {
        return PermissionRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new PermissionForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(PermissionDto data) {
        new PermissionForm(data, true);
    }

    @Override
    protected void edit(PermissionDto data) {
        new PermissionForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        PermissionRequests.getInstance().delete(id);
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
        component.setVisible(true);
    }
}
