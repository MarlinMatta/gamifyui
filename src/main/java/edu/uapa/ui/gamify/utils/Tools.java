package edu.uapa.ui.gamify.utils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.utils.HashUtils;
import edu.uapa.ui.gamify.models.LoginManager;
import edu.uapa.ui.gamify.routes.AllRoutes;

import java.util.Arrays;
import java.util.List;

import static edu.uapa.ui.gamify.routes.AllRoutes.LOGIN_ROUTE;

public class Tools {
    public static final String SESSION_NAME = "ChistMarioAndrewAdreanJesu";

    public static final List<Long> ITEMS_PER_PAGE = Arrays.asList(5L, 10L, 15L, 20L, 25L, 30L, 35L, 40L, 45L, 50L);

    public static final Long DEFAULT_ITEMS_PER_PAGE_VALUE = 15L;

    public static boolean isLogin() {
        return VaadinSession.getCurrent().getAttribute(SESSION_NAME) != null;
    }

    public static void navigateToApp() {
        UI.getCurrent().navigate(AllRoutes.APP_ROUTE);
//        UI.getCurrent().getPage().reload();
    }

    public static void navigateToLogin() {
        UI.getCurrent().navigate(LOGIN_ROUTE);
//        UI.getCurrent().getPage().reload();
    }

    public static void setSession(UserDto userDto) {
        VaadinSession.getCurrent().setAttribute(Tools.SESSION_NAME, new LoginManager(userDto));
    }

    public static void closeSession() {
        VaadinSession.getCurrent().close();
        navigateToLogin();
    }

    public static String toSHA256(String str) {
        return HashUtils.encodeSHA256(str);
    }
}
