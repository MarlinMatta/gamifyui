package edu.uapa.ui.gamify.routes.adminitrative;

import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.requests.login.LoginRequests;
import edu.uapa.ui.gamify.routes.AllRoutes;
import edu.uapa.ui.gamify.utils.Tools;
import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.utesa.lib.models.dtos.security.UserDto;


@Route(value = AllRoutes.LOGIN_ROUTE)
public class LoginRoute extends VerticalLayout {

    private LoginOverlay loginOverlay;
    private LoginI18n i18n;
    private boolean isStudent = false;

    public LoginRoute() {
        if (Tools.isLogin()) Tools.closeSession();
        initialized();
        setLanguage();
        setAction();
//        autoLogin();
    }

    private void initialized() {
        loginOverlay = new LoginOverlay();
        i18n = LoginI18n.createDefault();
        add(loginOverlay);
    }

    private void setLanguage() {
        loginOverlay.setTitle("Gametice 2.0");
        loginOverlay.setDescription("A Fun  Way To Learn");
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
            isStudent(userDto);
            if (isStudent) {
                Tools.navigateToStudentMainMenu();
            } else {
                Tools.navigateToApp();
            }
        }
    }

    private void isStudent(UserDto userDto) {
        userDto.getPermissions().forEach(this::accept);
    }

    private void accept(PermissionDto dto) {
        isStudent = dto.getCode() == 1;
    }

    private void autoLogin() {
        authentication("root", "root");
    }

}
