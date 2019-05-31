package edu.uapa.ui.gamify.routes;

import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "login")
public class LoginRoute extends VerticalLayout {

    private LoginOverlay loginOverlay;
    private LoginI18n i18n;

    public LoginRoute() {
        initialized();
        setLanguage();
        setAction();
    }

    private void initialized() {
        loginOverlay = new LoginOverlay();
        i18n = LoginI18n.createDefault();
        add(loginOverlay);
    }

    private void setLanguage() {
        loginOverlay.setTitle("Gamify Educator");
        loginOverlay.setDescription("Educational way to learn!");
        i18n.setErrorMessage(new LoginI18n.ErrorMessage());
    }

    private void setAction() {
        loginOverlay.addLoginListener(it -> Notification.show("Precionaste login"));
        loginOverlay.addForgotPasswordListener(it -> Notification.show("Por que olvidaste tu clave, no me jodas, idiota"));
        loginOverlay.setOpened(true);
    }
}
