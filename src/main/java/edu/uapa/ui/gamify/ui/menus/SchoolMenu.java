package edu.uapa.ui.gamify.ui.menus;

import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftBadgeIconItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.ui.tabs.security.ParameterTab;
import edu.uapa.ui.gamify.ui.tabs.security.PermissionGroupTab;
import edu.uapa.ui.gamify.ui.tabs.security.PermissionTab;
import edu.uapa.ui.gamify.ui.tabs.security.UserTab;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.enums.EnumLoanMasterPermission;

public class SchoolMenu extends AbstractView {

//    private CompanyDesign companyDesign = new CompanyDesign();

    public SchoolMenu() {
    }

    public Component getInstance() {
        return LeftSubMenuBuilder.get(Captions.SECURITY_MENU, VaadinIcon.SHIELD.create())
                .add(security(parameterMenuItem(), EnumLoanMasterPermission.PARAM.code))
                .add(security(permissionMenuItem(), EnumLoanMasterPermission.PERMISSION.code))
                .add(security(permissionGroupMenuItem(), EnumLoanMasterPermission.P_GROUP.code))
                .add(security(userMenuItem(), EnumLoanMasterPermission.USER.code))
                .build();
    }

    private Component b() {
        Button button = new Button("Company");
        Dialog dialog = new Dialog();
//        dialog.add(companyDesign);
        button.addClickListener(e -> dialog.open());
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        return button;
    }

    private Component parameterMenuItem() {
        return new LeftBadgeIconItem(Captions.PARAMETER_ITEM, VaadinIcon.COG.create(), event -> getTabsManager().addTab(Captions.PARAMETER_ITEM, new ParameterTab(), true));
    }

    private Component permissionMenuItem() {
        return new LeftBadgeIconItem(Captions.PERMISSION_ITEM,
                VaadinIcon.CHECK_SQUARE.create(),
                event -> getTabsManager().addTab(Captions.PERMISSION_ITEM, new PermissionTab(), true));
    }

    private Component permissionGroupMenuItem() {
        return new LeftBadgeIconItem(Captions.PERMISSION_GROUP_ITEM,
                VaadinIcon.GROUP.create(),
                event -> getTabsManager().addTab(Captions.PERMISSION_GROUP_ITEM, new PermissionGroupTab(), true));
    }

    private Component userMenuItem() {
        return new LeftBadgeIconItem(Captions.USER_ITEM,
                VaadinIcon.USER.create(),
                event -> getTabsManager().addTab(Captions.USER_ITEM, new UserTab(), true));
    }

    private Component security(Component component, int permissionCode) {
        if (getLoginManager().hasPermission(permissionCode)) {
            component.setVisible(false);
            return component;
        }
        return component;
    }
}
