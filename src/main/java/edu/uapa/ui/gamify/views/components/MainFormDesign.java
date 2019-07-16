package edu.uapa.ui.gamify.views.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.utils.captions.Captions;

/**
 * A Designer generated component for the main-form-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("main-form-design")
@HtmlImport("src/views/components/main-form-design.html")
public class MainFormDesign extends PolymerTemplate<MainFormDesign.MainFormDesignModel> {

    @Id("header")
    private Element header;
    @Id("btnPlay")
    private Button btnPlay;
    @Id("btnProfile")
    private Button btnProfile;
    @Id("btnSetting")
    private Button btnSetting;
    @Id("btnLogout")
    private Button btnLogout;

    /**
     * Creates a new MainFormDesign.
     */
    public MainFormDesign() {
        btnPlay.setText(Captions.PLAY);
        btnProfile.setText(Captions.PROFILE);
        btnSetting.setText(Captions.SETTING);
        btnLogout.setText(Captions.LOGOUT);
    }


    public void setPlayAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnPlay.addClickListener(clickEvent);
    }

    public void setProfileAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnProfile.addClickListener(clickEvent);
    }

    public void setSettingAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnSetting.addClickListener(clickEvent);
    }

    public void setLogoutAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnLogout.addClickListener(clickEvent);
    }


    /**
     * This model binds properties between MainFormDesign and main-form-design
     */
    public interface MainFormDesignModel extends TemplateModel {

    }
}
