package edu.uapa.ui.gamify.ui.components;

import com.github.appreciated.app.layout.component.menu.RoundImage;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.ui.menus.SchoolMenu;
import edu.uapa.ui.gamify.ui.menus.SecurityMenu;
import edu.utesa.lib.models.enums.EnumLoanMasterPermission;

public class LeftAppMenu extends AbstractView {

    public static Component building() {
        return LeftAppMenuBuilder
                .get()
                .addToSection(profile(), Section.HEADER)
                .add(security(new SchoolMenu().getInstance(), EnumLoanMasterPermission.ROOT.code))
                .add(security(new SecurityMenu().getInstance(), EnumLoanMasterPermission.ROOT.code))
                .build();
    }

    private static Component security(Component component, int permissionCode) {
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

//        RoundImage roundImage = new RoundImage("frontend/src/images/logo.jpeg", "100%", null);
        RoundImage roundImage = new RoundImage("frontend/src/images/books.png", "64px", null);
        layout.add(roundImage);

        Span description = new Span();
        description.setText("A Fun Way To Learn");
        description.getStyle().set("text-align", "center");
        layout.add(description);

        Span name = new Span();
        name.setText(getLoginManager().getName());
        name.getStyle().set("text-align", "center");
        layout.add(name);

        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, roundImage, name, description);
        return layout;
    }
}
