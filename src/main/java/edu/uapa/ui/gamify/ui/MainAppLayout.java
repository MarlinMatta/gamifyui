package edu.uapa.ui.gamify.ui;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import edu.uapa.ui.gamify.ui.components.AppBar;
import edu.uapa.ui.gamify.ui.components.LeftAppMenu;
import edu.uapa.ui.gamify.utils.Tools;

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainAppLayout extends AppLayoutRouterLayout {
    public MainAppLayout() {
        if (!Tools.isLogin()) {
            Tools.navigateToLogin();
        } else {
            init(AppLayoutBuilder
                    .get(Behaviour.LEFT_HYBRID)
                    .withTitle("Gamificacion de la Education")
                    .withAppBar(new AppBar().building())
                    .withAppMenu(LeftAppMenu.building())
                    .build());
        }
    }
}
