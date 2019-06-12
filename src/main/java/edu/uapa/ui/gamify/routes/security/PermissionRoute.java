package edu.uapa.ui.gamify.routes.security;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.uapa.ui.gamify.requests.security.PermissionRequests;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.AbstractPageWithGrid;
import edu.uapa.ui.gamify.ui.from.PermissionForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.uapa.ui.gamify.views.components.FormActionBarDesign;

import java.util.List;

import static edu.uapa.ui.gamify.routes.AllRoutes.APP_ROUTE;

@Route(value = APP_ROUTE, layout = MainAppLayout.class)
public final class PermissionRoute extends AbstractPageWithGrid<PermissionDto> {

    public PermissionRoute() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(PermissionDto::getCode).setHeader(Captions.GRID_COLUMN_CODE);
        getGrid().addColumn(PermissionDto::getName).setHeader(Captions.GRID_COLUMN_NAME);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows() {
        return PermissionRequests.getInstance().count();
    }

    @Override
    protected List<PermissionDto> getData(Long page, Long size, String searchValue) {
        return PermissionRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        PermissionForm permissionForm = new PermissionForm();
        permissionForm.setWidth("500px");
        permissionForm.add(new FormActionBarDesign());
        permissionForm.open();
        permissionForm.addDialogCloseActionListener(event -> {

        });


//        window.open();
//        add(window);
//        window.setSizeFull();
//        Notification.show("Start the search");
//        window.addDialogCloseActionListener(e -> {
//            window.close();
//            refresh();
//        });
    }

    @Override
    protected void view() {
        window.open();
        Notification.show("Start the visualization");
        window.addDialogCloseActionListener(e -> {
            window.close();
        });
    }

    @Override
    protected void edit() {
        window.open();
        Notification.show("Start the Edition");
        window.addDialogCloseActionListener(e -> {
            window.close();
        });
    }

    @Override
    protected void delete() {
        Notification.show("Start the erasing");
    }

    @Override
    protected void refresh() {
        Notification.show("Start the refreshing");
    }
}
