package edu.uapa.ui.gamify.ui.menus;

import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import edu.utesa.lib.models.enums.EnumLoanMasterPermission;
import edu.uapa.ui.gamify.ui.GridTest;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.utils.captions.Captions;

public class BusinessMenu extends AbstractView {

//    @Id("ParameterRoute-design")
//    private CompanyDesign companyDesign = new CompanyDesign();

    public BusinessMenu() {
    }

    public Component getInstance() {
        return LeftSubMenuBuilder.get(Captions.BUSINESS_MENU, VaadinIcon.OFFICE.create())
                .add(security(companyMenuItem(), EnumLoanMasterPermission.COMPANY.code))
                .add(security(BranchMenuItem(), EnumLoanMasterPermission.BRANCH.code))
                .add(security(DepartmentMenuItem(), EnumLoanMasterPermission.DEPARTMENT.code))
                .add(security(PositionMenuItem(), EnumLoanMasterPermission.POSITION.code))
                .add(security(EmployeeMenuItem(), EnumLoanMasterPermission.EMPLOYEE.code))
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

    private Component companyMenuItem() {
        return new LeftNavigationItem(Captions.COMPANY_ITEM,
                VaadinIcon.WORKPLACE.create(),
                GridTest.class);
    }

    private Component BranchMenuItem() {
        return new LeftNavigationItem(Captions.BRANCH_ITEM,
                VaadinIcon.PHONE_LANDLINE.create(),
                GridTest.class);
    }

    private Component DepartmentMenuItem() {
        return new LeftNavigationItem(Captions.DEPARTMENT_ITEM,
                VaadinIcon.CALENDAR_CLOCK.create(),
                GridTest.class);
    }

    private Component PositionMenuItem() {
        return new LeftNavigationItem(Captions.POSITION_ITEM,
                VaadinIcon.TASKS.create(),
                GridTest.class);
    }

    private Component EmployeeMenuItem() {
        return new LeftNavigationItem(Captions.EMPLOYEE_ITEM,
                VaadinIcon.MALE.create(),
                GridTest.class);
    }

    private Component security(Component component, int permissionCode) {
        if (getLoginManager().hasPermission(permissionCode)) {
            component.setVisible(false);
            return component;
        }
        return component;
    }
}
