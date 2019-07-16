package edu.uapa.ui.gamify.ui.components;

import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;

public class AppBar extends AppBarBuilder {
    private DefaultNotificationHolder notifications = new DefaultNotificationHolder();

    public AppBar() {
        building();
    }

    public Component building() {
        notifications.addClickListener(notification -> Notification.show(notification.getTitle()));
        return get()
                .add(new AppBarNotificationButton<>(VaadinIcon.BELL, notifications))
                .add(closeButton())
                .build();
    }

    private Button closeButton() {
        Button button = new Button(Captions.LOGOUT);
        button.setIcon(VaadinIcon.EXIT.create());
        button.addClickListener(it -> Tools.closeSession());
        return button;
    }
}
