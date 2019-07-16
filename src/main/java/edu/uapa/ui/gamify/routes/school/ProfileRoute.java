package edu.uapa.ui.gamify.routes.school;

import com.github.appreciated.app.layout.component.menu.RoundImage;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawerLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.routes.AllRoutes;
import edu.uapa.ui.gamify.ui.MainAppLayout;
import edu.uapa.ui.gamify.ui.abstracts.PageView;

@Route(value = AllRoutes.PROFILE_ROUTE, layout = MainAppLayout.class)
public class ProfileRoute extends PageView {

    private AppDrawerLayout mainLayout = new AppDrawerLayout();

    public ProfileRoute() {
        initialized();
    }

    private void initialized() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        getStyle().set("border", "1px solid green");

        buildMainLayout();
        add(mainLayout);

        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }

    private void buildMainLayout() {
        mainLayout.getElement().getStyle().set("width", "100%");
        mainLayout.getElement().getStyle().set("border", "5px solid purple");

//        mainLayout.addItem(achievements());
//        mainLayout.addItem(history());
//        mainLayout.addItem(rewards());
    }

    private Component achievements() {
        VerticalLayout layout = new VerticalLayout();
        layout.add(topLeft("achievements", "purple"));
        layout.add(img(""));
        return layout;
    }

    private Component history() {
        VerticalLayout layout = new VerticalLayout();
        layout.add(topLeft("history", "green"));
        layout.add(img(""));

        return layout;
    }

    private Component rewards() {
        VerticalLayout layout = new VerticalLayout();
        layout.add(topLeft("rewards", "orange"));
        layout.add(img(""));

        return layout;
    }

    private Button topLeft(String name, String color) {
        Button button = new Button(name);
        button.setWidth("150px");
        button.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.MATERIAL_CONTAINED);
        button.getStyle().set("background", color);
        return button;
    }

    private RoundImage img(String imgAddress) {
        return new RoundImage("frontend/src/images/logo.png", "150px", "150px");
    }
}
