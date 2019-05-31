package edu.uapa.ui.gamify.routes;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.utils.Constants;

@Route(Constants.MAIN_ROUTE)
public class MainRoute extends Div {

    private VerticalLayout verticalLayout = new VerticalLayout();
    private Button btnPlay;
    private Button btnProfile;
    private Button btnSetting;
    private Dialog window;

    public MainRoute() {
        initialized();
        setLanguage();

        //add(window);
        window.add(verticalLayout);
//        verticalLayout.setSpacing(true);
//        verticalLayout.setMargin(true);

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
