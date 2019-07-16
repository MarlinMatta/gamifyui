package edu.uapa.ui.gamify.ui.abstracts.base;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.server.VaadinSession;
import edu.uapa.ui.gamify.models.LoginManager;
import edu.uapa.ui.gamify.utils.Tools;

public abstract class AbstractDialog extends Dialog {

    protected AbstractDialog() {
        build();
    }

    protected static LoginManager getLoginManager() {
        return (LoginManager) VaadinSession.getCurrent().getAttribute(Tools.SESSION_NAME);
    }

    private void build() {
        this.setCloseOnEsc(true);
        this.setCloseOnOutsideClick(false);
    }
}