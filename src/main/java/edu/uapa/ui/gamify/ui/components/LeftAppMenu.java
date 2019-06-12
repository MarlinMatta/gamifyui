package edu.uapa.ui.gamify.ui.components;

import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.vaadin.flow.component.Component;
import edu.utesa.lib.models.enums.EnumLoanMasterPermission;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.ui.menus.BusinessMenu;
import edu.uapa.ui.gamify.ui.menus.SecurityMenu;

public class LeftAppMenu extends AbstractView {


    public static Component building() {
        return LeftAppMenuBuilder
                .get()
//                .addToSection(new RoundImage("../src/images/logo.png", "100%", "150px"), HEADER)
                .add(security(new BusinessMenu().getInstance(), EnumLoanMasterPermission.COMPANY_MENU.code))
                .add(security(SecurityMenu.getInstance(), EnumLoanMasterPermission.SECURITY_MENU.code))
                .build();
    }

    private static Component security(Component component, int permissionCode) {
        if (getLoginManager().hasPermission(permissionCode)) {
            component.setVisible(false);
            return component;
        }
        return component;
    }
}
