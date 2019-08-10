package edu.uapa.ui.gamify.ui.components;

import com.github.appreciated.app.layout.component.menu.RoundImage;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import edu.uapa.ui.gamify.requests.school.StudentRequests;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.ui.menus.SchoolMenu;
import edu.uapa.ui.gamify.ui.menus.SecurityMenu;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;

public class LeftAppMenu extends AbstractView {

    public static Component building() {
        return LeftAppMenuBuilder
                .get()
                .addToSection(profile(), Section.HEADER)
                .add(security(new SchoolMenu().getInstance(), 2))
                .add(security(new SecurityMenu().getInstance(), 2))
                .build();
    }

    private static Component security(Component component, int permissionCode) {
        if (getLoginManager().hasPermission(0))
            if (getLoginManager().hasPermission(permissionCode)) {
                component.setVisible(false);
                return component;
            }
        return component;
    }

    private static Component profile() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);
        layout.setSpacing(true);
        layout.setPadding(true);

        RoundImage roundImage = new RoundImage("frontend/src/images/LeftLogoo.jpeg", "150px", null);
        layout.add(roundImage);

        Span description = new Span();
        description.setText("A fun way to learn");
        description.getStyle().set("text-align", "center");
        layout.add(description);

        Span name = new Span();
        name.setText(getLoginManager().getName());
        name.getStyle().set("text-align", "center");
        Span point = new Span();
        String poin = StudentRequests.getInstance().refreshByUser(getLoginManager().getId()).getPoints() + "";
        point.setText("Puntos acumulados: " + (poin != null ? poin : "0"));
        point.getStyle().set("text-align", "center");
        Button button = closeButton();

        layout.add(name);
        layout.add(point);
        layout.add(button);

        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, roundImage, name, description);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, roundImage, button, description);
        return layout;
    }

    private static Button closeButton() {
        Button button = new Button(Captions.LOGOUT);
        button.setIcon(VaadinIcon.EXIT.create());
        button.addClickListener(it -> Tools.closeSession());
        return button;
    }
}
