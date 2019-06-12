package edu.uapa.ui.gamify.routes.adminitrative;

import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.uapa.ui.gamify.requests.login.LoginRequests;
import edu.uapa.ui.gamify.utils.Tools;

import static edu.uapa.ui.gamify.routes.AllRoutes.LOGIN_ROUTE;


@Route(value = LOGIN_ROUTE)
public class LoginRoute extends VerticalLayout {

    private LoginOverlay loginOverlay;
    private LoginI18n i18n;

    public LoginRoute() {
        if (Tools.isLogin()) Tools.closeSession();
        initialized();
        setLanguage();
        setAction();

        autoLogin();
    }

    private void initialized() {
        loginOverlay = new LoginOverlay();
        i18n = LoginI18n.createDefault();
        add(loginOverlay);
    }

    private void setLanguage() {
        loginOverlay.setTitle("Loan Master");
        loginOverlay.setDescription("The best Loan Software in Dominican Republic to Garroteros");
        i18n.setErrorMessage(new LoginI18n.ErrorMessage());
    }

    private void setAction() {
        loginOverlay.addLoginListener(it -> authentication(it.getUsername(), it.getPassword()));
        loginOverlay.addForgotPasswordListener(it -> Notification.show("Por que olvidaste tu clave"));
        loginOverlay.setOpened(true);
    }

    private void authentication(String userName, String password) {
        UserDto userDto = LoginRequests.getInstance().login(userName, password);
        if (userDto == null) {
            loginOverlay.setError(true);
        } else {
            loginOverlay.close();
            Tools.setSession(userDto);
            Tools.navigateToApp();
        }
    }

    private void autoLogin() {
        authentication("root", "root");
    }
}
