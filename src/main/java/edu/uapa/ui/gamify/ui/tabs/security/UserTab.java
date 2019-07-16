package edu.uapa.ui.gamify.ui.tabs.security;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.security.UserRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.from.UserForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.security.UserDto;

import java.util.List;

public class UserTab extends AbstractTabWithGrid<UserDto> {

    public UserTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(UserDto::getNickName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(UserDto::getMail).setHeader(Captions.GRID_COLUMN_EMAIL);
        getGrid().addColumn(UserDto::getAdmin).setHeader(Captions.GRID_COLUMN_ADMIN);
        getGrid().addColumn(UserDto::getLanguage).setHeader(Captions.GRID_COLUMN_LANGUAGE);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return UserRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<UserDto> getData(Long page, Long size, String searchValue) {
        return UserRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new UserForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(UserDto data) {
        new UserForm(data, true);
    }

    @Override
    protected void edit(UserDto data) {
        new UserForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        UserRequests.getInstance().delete(id);
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
