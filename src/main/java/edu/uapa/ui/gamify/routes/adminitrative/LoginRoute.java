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
    private boolean isTeacher = false;

    public LoginRoute() {
        if (Tools.isLogin()) Tools.closeSession();
        initialized();
        setLanguage();
        setAction();
    }

    private void initialized() {
        loginOverlay = new LoginOverlay();
        i18n = LoginI18n.createDefault();
        add(loginOverlay);
        loginOverlay.setI18n(i18n);
    }

    private void setLanguage() {
        loginOverlay.setTitle("Gametice");
        loginOverlay.setDescription("Una forma divertida de aprender");
        i18n.setErrorMessage(new LoginI18n.ErrorMessage());
        i18n.getForm().setUsername("Usario ");
        i18n.getForm().setPassword("Clave");
        i18n.getForm().setSubmit("Iniciar sesión");
        i18n.getForm().setForgotPassword("Olvide mi clave");
        loginOverlay.setI18n(i18n);
    }

    private void setAction() {
        loginOverlay.addLoginListener(it -> authentication(it.getUsername(), it.getPassword()));
        loginOverlay.addForgotPasswordListener(it -> Notification.show("Favor contactar el administrador"));
        loginOverlay.setOpened(true);
    }

    private void authentication(String userName, String password) {
        UserDto userDto = LoginRequests.getInstance().login(userName, password);
        if (userDto == null) {
            loginOverlay.setError(true);
        } else {
            loginOverlay.close();
            Tools.setSession(userDto);
            getUserType(userDto);
            if (isStudent) {
                Tools.navigateToStudentMainMenu();
            } else if (isTeacher) {
                Tools.navigateToTeacherMainMenu();
            } else {
                Tools.navigateToApp();
            }
        }
    }

    private void getUserType(UserDto userDto) {
        userDto.getPermissions().forEach(this::accept);
    }

    private void accept(PermissionDto dto) {
        isStudent = dto.getCode() == 1;
        isTeacher = dto.getCode() == 2;
    }
}
