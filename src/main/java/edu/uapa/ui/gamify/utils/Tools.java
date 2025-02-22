package edu.uapa.ui.gamify.utils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.server.VaadinSession;
import edu.uapa.ui.gamify.models.LoginManager;
import edu.uapa.ui.gamify.routes.AllRoutes;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.ui.components.TabsManager;
import edu.utesa.lib.models.dtos.configurations.ConfigurationDto;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.utils.HashUtils;

import java.util.Arrays;
import java.util.List;

import static edu.uapa.ui.gamify.routes.AllRoutes.*;

public class Tools extends PageView {
    public static final String SESSION_NAME = "ChistMarioAndrewAdreanJesu";

    public static final String SESSION_TAB_NAME = "MinombreesKkwazzawazzakkwaquikkwalaquaza_Zzabolazza";
    public static final String SESSION_GAME_MODE = "Kkwazzawazzakkwaquikkwalaquaza";

    public static final String SESSION_SUBJECT = "waquikkwalaquaza";
    public static final String SESSION_EXAM = "waquikkwalaquazacdsefcevsdvcsas";
    public static final String SESSION_TOPIC = "Kkwazzawazzakkwa";
    public static final String SESSION_CONFIGURATION = "MarlinMattaAzarta";

    public static final List<Long> ITEMS_PER_PAGE = Arrays.asList(5L, 10L, 15L, 20L, 25L, 30L, 35L, 40L, 45L, 50L);

    public static final Long DEFAULT_ITEMS_PER_PAGE_VALUE = 15L;

    public static int totalPoints = 0;

    public static boolean isLogin() {
        return VaadinSession.getCurrent().getAttribute(SESSION_NAME) != null;
    }

    public static void navigateToApp() {
        UI.getCurrent().navigate(AllRoutes.APP_ROUTE);
    }

    public static void navigateToConfiguration() {
        UI.getCurrent().navigate(AllRoutes.CONFIGURATION_ROUTE);
    }

    public static void navigateToTest() {
        UI.getCurrent().navigate(AllRoutes.TEST_ROUTE);
    }

    public static void navigateToPlay() {
        UI.getCurrent().navigate(PLAY_ROUTE);
    }

    public static void navigateToLearn() {
        UI.getCurrent().navigate(AllRoutes.LEARN_ROUTE);
    }

    public static void navigateToTopic() {
        UI.getCurrent().navigate(AllRoutes.TOPIC_ROUTE);
    }

    public static void navigateToLogin() {
        UI.getCurrent().navigate(LOGIN_ROUTE);
    }

    public static void navigateToChooseSubject() {
        UI.getCurrent().navigate(AllRoutes.CHOOSE_SUBJECT_ROUTE);
    }

    public static void navigateToChooseExam() {
        UI.getCurrent().navigate(AllRoutes.CHOOSE_TEST_ROUTE);
    }

    public static void navigateToProfile() {
        UI.getCurrent().navigate(AllRoutes.PROFILE_ROUTE);
    }

    public static void navigateToGame() {
        UI.getCurrent().navigate(AllRoutes.PROBLEM_ROUTE);
    }

    public static void navigateToSummitResult() {
        UI.getCurrent().navigate(AllRoutes.SUMMIT_RESULT);
    }

    public static void navigateToStudentMainMenu() {
        UI.getCurrent().navigate(STUDENT_MAIN_MENU_ROUTE);
        if (VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE) != null && VaadinSession.getCurrent().getAttribute(Tools.SESSION_GAME_MODE).equals("Play")) {
            UI.getCurrent().getPage().reload();
        }
    }

    public static void navigateToTeacherMainMenu() {
        UI.getCurrent().navigate(APP_ROUTE);
    }

    public static void setSession(UserDto userDto) {
        VaadinSession.getCurrent().setAttribute(Tools.SESSION_NAME, new LoginManager(userDto));
    }

    public static void setSessionSubject(String id) {
        VaadinSession.getCurrent().setAttribute(Tools.SESSION_SUBJECT, id);
    }

    public static String getSessionSubject() {
        return (String) VaadinSession.getCurrent().getAttribute(Tools.SESSION_SUBJECT);
    }

    public static Long getUserId() {
        return getLoginManager().getId();
    }

    public static void setSessionTopic(String id) {
        VaadinSession.getCurrent().setAttribute(Tools.SESSION_TOPIC, id);
    }

    public static void setSessionExam(String id) {
        VaadinSession.getCurrent().setAttribute(Tools.SESSION_EXAM, id);
    }

    public static String getSessionExam() {
        return (String) VaadinSession.getCurrent().getAttribute(Tools.SESSION_EXAM);
    }

    public static String getSessionTopic() {
        return (String) VaadinSession.getCurrent().getAttribute(Tools.SESSION_TOPIC);
    }

    public static void setSessionConfiguration(ConfigurationDto dto) {
        VaadinSession.getCurrent().setAttribute(Tools.SESSION_CONFIGURATION, dto);
    }

    public static ConfigurationDto getSessionConfiguration() {
        return (ConfigurationDto) VaadinSession.getCurrent().getAttribute(Tools.SESSION_CONFIGURATION);
    }

    public static void initTabs() {
        VaadinSession.getCurrent().setAttribute(Tools.SESSION_TAB_NAME, new TabsManager(new Tabs()));
    }

    public static void closeSession() {
        VaadinSession.getCurrent().close();
        navigateToLogin();
    }

    public static String toSHA256(String str) {
        return HashUtils.encodeSHA256(str);
    }
}
