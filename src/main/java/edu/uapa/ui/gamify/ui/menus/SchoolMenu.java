package edu.uapa.ui.gamify.ui.menus;

import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftBadgeIconItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.ui.tabs.gamifies.ProblemTab;
import edu.uapa.ui.gamify.ui.tabs.gamifies.TopicTab;
import edu.uapa.ui.gamify.ui.tabs.school.*;
import edu.uapa.ui.gamify.ui.tabs.security.UserTab;
import edu.uapa.ui.gamify.utils.captions.Captions;

public class SchoolMenu extends AbstractView {

    public SchoolMenu() {
    }

    public Component getInstance() {
        return LeftSubMenuBuilder.get(Captions.SCHOOL_MENU, VaadinIcon.BUILDING.create())
                .add(security(schoolMenuItem(), 0))
                .add(security(subjectMenuItem(), 2))
                .add(security(gradeMenuItem(), 2))
                .add(security(teacherMenuItem(), 0))
                .add(security(studentMenuItem(), 0))
                .add(security(topicMenuItem(), 2))
                .add(security(problemMenuItem(), 2))
                .add(security(quitMenuItem(), 2))
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

    private Component teacherMenuItem() {
        return new LeftBadgeIconItem(Captions.TEACHER_ITEM,
                VaadinIcon.USER.create(),
                event -> getTabsManager().addTab(Captions.TEACHER_ITEM, new TeacherTab(), true));
    }

    private Component studentMenuItem() {
        return new LeftBadgeIconItem(Captions.STUDENT_ITEM,
                VaadinIcon.USER.create(),
                event -> getTabsManager().addTab(Captions.STUDENT_ITEM, new StudentTab(), true));
    }

    private Component topicMenuItem() {
        return new LeftBadgeIconItem(Captions.TOPIC_ITEM,
                VaadinIcon.LINES_LIST.create(),
                event -> getTabsManager().addTab(Captions.TOPIC_ITEM, new TopicTab(), true));
    }

    private Component problemMenuItem() {
        return new LeftBadgeIconItem(Captions.PROBLEM_ITEM,
                VaadinIcon.QUESTION.create(),
                event -> getTabsManager().addTab(Captions.PROBLEM_ITEM, new ProblemTab(), true));
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
