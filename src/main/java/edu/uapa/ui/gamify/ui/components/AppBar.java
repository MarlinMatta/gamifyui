package edu.uapa.ui.gamify.ui.components;

import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;

public class AppBar extends AppBarBuilder {
    private DefaultNotificationHolder notifications = new DefaultNotificationHolder();

    public AppBar() {
        building();
    }

    public Component building() {
        notifications.addClickListener(notification -> Notification.show(notification.getTitle()));
        return get()
                .add(new AppBarNotificationButton<>(VaadinIcon.BELL, notifications))
                .build();
    }
}
