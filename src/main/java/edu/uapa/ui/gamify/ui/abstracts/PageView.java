package edu.uapa.ui.gamify.ui.abstracts;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;

public abstract class PageView extends AbstractView {

    protected PageView() {
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.START);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }
}