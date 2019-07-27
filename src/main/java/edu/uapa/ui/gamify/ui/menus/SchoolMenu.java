package edu.uapa.ui.gamify.ui.menus;

import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftBadgeIconItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.ui.form.school.SchoolForm;
import edu.uapa.ui.gamify.ui.tabs.school.GradeTab;
import edu.uapa.ui.gamify.ui.tabs.school.SchoolTab;
import edu.uapa.ui.gamify.ui.tabs.school.SubjectTab;
import edu.uapa.ui.gamify.ui.tabs.school.TopicTab;
import edu.uapa.ui.gamify.ui.tabs.security.ParameterTab;
import edu.uapa.ui.gamify.ui.tabs.security.PermissionGroupTab;
import edu.uapa.ui.gamify.ui.tabs.security.PermissionTab;
import edu.uapa.ui.gamify.ui.tabs.security.UserTab;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.enums.EnumLoanMasterPermission;

public class SchoolMenu extends AbstractView {

    public SchoolMenu() {
    }

    public Component getInstance() {
        return LeftSubMenuBuilder.get(Captions.SCHOOL_MENU, VaadinIcon.BUILDING.create())
                .add(security(schoolMenuItem(), EnumLoanMasterPermission.PARAM.code))
                .add(security(subjectMenuItem(), EnumLoanMasterPermission.PERMISSION.code))
                .add(security(gradeMenuItem(), EnumLoanMasterPermission.PERMISSION.code))
                .add(security(topicMenuItem(), EnumLoanMasterPermission.P_GROUP.code))
                .add(security(problemMenuItem(), EnumLoanMasterPermission.USER.code))
                .add(security(quitMenuItem(), EnumLoanMasterPermission.USER.code))
                .build();
    }

    private Component schoolMenuItem() {
        return new LeftBadgeIconItem(Captions.SCHOOL_ITEM,
                VaadinIcon.BUILDING_O.create(),
                event -> getTabsManager().addTab(Captions.SCHOOL_ITEM, new SchoolTab(), true));
    }

    private Component subjectMenuItem() {
        return new LeftBadgeIconItem(Captions.SUBJECT_ITEM,
                VaadinIcon.OPEN_BOOK.create(),
                event -> getTabsManager().addTab(Captions.SUBJECT_ITEM, new SubjectTab(), true));
    }

    private Component gradeMenuItem() {
        return new LeftBadgeIconItem(Captions.GRADE_ITEM,
                VaadinIcon.OPEN_BOOK.create(),
                event -> getTabsManager().addTab(Captions.GRADE_ITEM, new GradeTab(), true));
    }

    private Component topicMenuItem() {
        return new LeftBadgeIconItem(Captions.TOPIC_ITEM,
                VaadinIcon.LINES_LIST.create(),
                event -> getTabsManager().addTab(Captions.TOPIC_ITEM, new TopicTab(), true));
    }

    private Component problemMenuItem() {
        return new LeftBadgeIconItem(Captions.PROBLEM_ITEM,
                VaadinIcon.QUESTION.create(),
                event -> getTabsManager().addTab(Captions.PROBLEM_ITEM, new UserTab(), true));
    }

    private Component quitMenuItem() {
        return new LeftBadgeIconItem(Captions.QUIT_ITEM,
                VaadinIcon.EDIT.create(),
                event -> getTabsManager().addTab(Captions.QUIT_ITEM, new UserTab(), true));
    }

    private Component security(Component component, int permissionCode) {
        if (getLoginManager().hasPermission(permissionCode)) {
            component.setVisible(false);
            return component;
        }
        return component;
    }
}
