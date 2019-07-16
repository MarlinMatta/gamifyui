package edu.uapa.ui.gamify.views.security;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.models.enums.Language;

/**
 * A Designer generated component for the user-form-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("user-form-design")
@HtmlImport("src/views/security/user-form-design.html")
public class UserFormDesign extends PolymerTemplate<UserFormDesign.UserFormDesignModel> implements FormStructure<UserDto> {

    @Id("tfUserName")
    private TextField tfUserName;
    @Id("pfPassword")
    private PasswordField pfPassword;
    @Id("cbLanguage")
    private ComboBox<Language> cbLanguage;
    @Id("efEmail")
    private EmailField efEmail;
    @Id("btnPermission")
    private Button btnPermission;

    /**
     * Creates a new UserFormDesign.
     */
    public UserFormDesign() {
        tfUserName.setLabel(Captions.USER_NAME);
        tfUserName.setRequired(true);

        pfPassword.setLabel(Captions.PASSWORD);
        pfPassword.setRequired(true);

        cbLanguage.setLabel(Captions.LANGUAGE);
        cbLanguage.setRequired(true);

        efEmail.setLabel(Captions.EMAIL);

        btnPermission.setText(Captions.PERMISSION);

        this.getElement().getStyle().set("max-width", "720px");
        setAction();
    }

    public void setPermissionAction(ComponentEventListener<ClickEvent<Button>> clickEvent) {
        btnPermission.addClickListener(clickEvent);
    }

    private void setAction() {
        cbLanguage.setItems(Language.values());
    }

    @Override
    public void restore(UserDto data) {
        tfUserName.setValue(data.getNickName());
        pfPassword.setValue("qwerty");
        cbLanguage.setValue(data.getLanguage());
        efEmail.setValue(data.getMail());
    }

    @Override
    public void visualize() {
        tfUserName.setReadOnly(true);
        pfPassword.setReadOnly(true);
        cbLanguage.setReadOnly(true);
        efEmail.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        return !tfUserName.isInvalid() && !pfPassword.isInvalid() && !cbLanguage.isInvalid() && !efEmail.isInvalid();
    }

    @Override
    public UserDto collectData(UserDto model) {
        model.setNickName(tfUserName.getValue());
        model.setPassword(pfPassword.getValue());
        model.setLanguage(cbLanguage.getValue());
        model.setMail(efEmail.getValue());
        return model;
    }

    @Override
    public void security() {

    }


    /**
     * This model binds properties between UserFormDesign and user-form-design
     */
    public interface UserFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
