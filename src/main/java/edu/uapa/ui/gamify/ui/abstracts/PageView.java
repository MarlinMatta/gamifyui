package edu.uapa.ui.gamify.ui.abstracts;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;

public abstract class PageView extends AbstractView {

    protected PageView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }
}