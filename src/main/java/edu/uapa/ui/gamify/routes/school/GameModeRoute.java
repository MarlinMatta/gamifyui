package edu.uapa.ui.gamify.routes.school;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import edu.uapa.ui.gamify.ui.abstracts.PageView;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.views.components.MainFormDesign;

import static edu.uapa.ui.gamify.routes.AllRoutes.STUDENT_MAIN_MENU_ROUTE;

@Route(STUDENT_MAIN_MENU_ROUTE)
public class GameModeRoute extends PageView {

    @Id("main-form-design")
    private MainFormDesign formDesign = new MainFormDesign();
    private Dialog window = new Dialog();

    public GameModeRoute() {
        initialized();
        setAction();
    }

    private void initialized() {
        window.setCloseOnEsc(false);
        window.setCloseOnOutsideClick(false);
        window.add(formDesign);
        window.setWidth("250px");
        window.open();
    }

    private void setAction() {
        formDesign.setPlayAction(event -> {
            Tools.navigateToChooseSubject();
            VaadinSession.getCurrent().setAttribute(Tools.SESSION_GAME_MODE, "Aprender");
            window.close();
        });
        formDesign.setProfileAction(event -> {
            VaadinSession.getCurrent().setAttribute(Tools.SESSION_GAME_MODE, "Practicar");
            Tools.navigateToChooseSubject();
            window.close();
        });
        formDesign.setSettingAction(event -> {
            VaadinSession.getCurrent().setAttribute(Tools.SESSION_GAME_MODE, "Pruebas");
            Tools.navigateToChooseSubject();
            window.close();
        });
        formDesign.setLogoutAction(event -> {
            Tools.closeSession();
            window.close();
        });
    }
}
