package edu.uapa.ui.gamify.routes.adminitrative;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import static edu.uapa.ui.gamify.routes.AllRoutes.GAME_MODE_ROUTE;

@Route(GAME_MODE_ROUTE)
public class GameModeRoute extends Div {

    private Button btnPlay;
    private Button btnProfile;
    private Button btnSetting;
    private Dialog window;

    public GameModeRoute() {
        initialized();
        setLanguage();

        VerticalLayout verticalLayout = new VerticalLayout();
        window.add(verticalLayout);

        btnPlay.setWidthFull();
        verticalLayout.add(btnPlay);
        btnProfile.setWidthFull();
        verticalLayout.add(btnProfile);
        btnSetting.setWidthFull();
        verticalLayout.add(btnSetting);
    }

    private void initialized() {
        btnPlay = new Button();
        btnProfile = new Button();
        btnSetting = new Button();

        window = new Dialog();
        window.setCloseOnEsc(false);
        window.setCloseOnOutsideClick(false);
        window.open();
    }

    private void setLanguage() {
        btnPlay.setText("Play");
        btnProfile.setText("Profile");
        btnSetting.setText("Setting");
    }
}
