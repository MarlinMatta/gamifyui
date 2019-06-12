package edu.uapa.ui.gamify.ui;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import edu.uapa.ui.gamify.ui.components.AppBar;
import edu.uapa.ui.gamify.ui.components.LeftAppMenu;

/**
 * The main views contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainAppLayout extends AppLayoutRouterLayout {
    public MainAppLayout() {
        init(AppLayoutBuilder
                .get(Behaviour.LEFT_HYBRID)
                .withTitle("Loan Master")
                .withAppBar(new AppBar().building())
                .withAppMenu(LeftAppMenu.building())
                .build());
    }
}
