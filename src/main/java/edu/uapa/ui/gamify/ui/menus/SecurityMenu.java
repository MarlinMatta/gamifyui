package edu.uapa.ui.gamify.ui.menus;

import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import edu.utesa.lib.models.enums.EnumLoanMasterPermission;
import edu.uapa.ui.gamify.routes.security.PermissionRoute;
import edu.uapa.ui.gamify.ui.GridTest;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.utils.captions.Captions;

public class SecurityMenu extends AbstractView {
    private SecurityMenu() {
    }

    public static Component getInstance() {
        return LeftSubMenuBuilder.get(Captions.SECURITY_MENU, VaadinIcon.SHIELD.create())
                .add(security(parameterMenuItem(), EnumLoanMasterPermission.PARAM.code))
                .add(security(permissionMenuItem(), EnumLoanMasterPermission.PERMISSION.code))
                .add(security(permissionGroupMenuItem(), EnumLoanMasterPermission.P_GROUP.code))
                .add(security(userMenuItem(), EnumLoanMasterPermission.USER.code))
                .build();
    }

    private static Component parameterMenuItem() {
        return new LeftNavigationItem(Captions.PARAMETER_ITEM,
                VaadinIcon.COG.create(),
                GridTest.class);
    }

    private static Component permissionMenuItem() {
        return new LeftNavigationItem(Captions.PERMISSION_ITEM,
                VaadinIcon.CHECK_SQUARE.create(),
                PermissionRoute.class);
    }

    private static Component permissionGroupMenuItem() {
        return new LeftNavigationItem(Captions.PERMISSION_GROUP_ITEM,
                VaadinIcon.GROUP.create(),
                GridTest.class);
    }

    private static Component userMenuItem() {
        return new LeftNavigationItem(Captions.USER_ITEM,
                VaadinIcon.USER.create(),
                GridTest.class);
    }

    private static Component security(Component component, int permissionCode) {
        if (getLoginManager().hasPermission(permissionCode)) {
            component.setVisible(false);
            return component;
        }
        return component;
    }
}
