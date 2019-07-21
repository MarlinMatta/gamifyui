package edu.uapa.ui.gamify.ui.tabs.security;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.security.PermissionRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.security.PermissionGroupForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.security.PermissionGroupDto;

import java.util.List;

public class PermissionGroupTab extends AbstractTabWithGrid<PermissionGroupDto> {

    public PermissionGroupTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(PermissionGroupDto::getName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(PermissionGroupDto::getDescription).setHeader(Captions.GRID_COLUMN_DESCRIPTION);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return PermissionRequests.getInstance().countGroup(searchValue);
    }

    @Override
    protected List<PermissionGroupDto> getData(Long page, Long size, String searchValue) {
        return PermissionRequests.getInstance().getGroups(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new PermissionGroupForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(PermissionGroupDto data) {
        new PermissionGroupForm(data, true);
    }

    @Override
    protected void edit(PermissionGroupDto data) {
        new PermissionGroupForm(data, false).addOpenedChangeListener(event -> getGrid().getDataProvider().refreshAll());
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
