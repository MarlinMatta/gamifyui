package edu.uapa.ui.gamify.ui.tabs.security;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.security.ParameterRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.security.ParameterForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.security.ParamDto;

import java.util.List;

public class ParameterTab extends AbstractTabWithGrid<ParamDto> {

    public ParameterTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(ParamDto::getCode).setHeader(Captions.GRID_COLUMN_CODE);
        getGrid().addColumn(ParamDto::getName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(ParamDto::getValue).setHeader(Captions.GRID_COLUMN_VALUE);
        getGrid().addColumn(ParamDto::getChangeRoot).setHeader(Captions.GRID_COLUMN_ONLY_ROOT);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return ParameterRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<ParamDto> getData(Long page, Long size, String searchValue) {
        return ParameterRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new ParameterForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(ParamDto data) {
        new ParameterForm(data, true);
    }

    @Override
    protected void edit(ParamDto data) {
        new ParameterForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        ParameterRequests.getInstance().delete(id);
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
