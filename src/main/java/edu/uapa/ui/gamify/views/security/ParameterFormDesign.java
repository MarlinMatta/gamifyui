package edu.uapa.ui.gamify.views.security;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.security.ParamDto;
import edu.utesa.lib.models.enums.type.security.EnumParamValueType;

/**
 * A Designer generated component for the parameter-form-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("parameter-form-design")
@HtmlImport("src/views/security/parameter-form-design.html")
public class ParameterFormDesign extends PolymerTemplate<ParameterFormDesign.ParameterFormDesignModel> implements FormStructure<ParamDto> {

    @Id("tfName")
    private TextField tfName;
    @Id("tfValue")
    private TextField tfValue;
    @Id("cbValue")
    private ComboBox<Boolean> cbValue;
    @Id("cbType")
    private ComboBox<EnumParamValueType> cbType;
    @Id("taDescription")
    private TextArea taDescription;
    @Id("chOnlyRoot")
    private Checkbox chOnlyRoot;
    @Id("tfCode")
    private NumberField tfCode;

    /**
     * Creates a new ParameterFormDesign.
     */
    public ParameterFormDesign() {
        tfCode.setLabel(Captions.CODE);

        tfName.setLabel(Captions.NAME);
        tfName.setRequired(true);

        cbType.setLabel(Captions.TYPE);
        cbType.setRequired(true);

        tfValue.setLabel(Captions.VALUE);

        cbValue.setLabel(Captions.VALUE);
        cbValue.setRequired(true);

        taDescription.setLabel(Captions.DESCRIPTION);
        taDescription.setRequired(true);

        chOnlyRoot.setLabel(Captions.ONLY_ROOT);

        this.getElement().getStyle().set("max-width", "720px");
        setAction();
    }

    private void setAction() {
        cbValue.setVisible(false);
        cbType.setItems(EnumParamValueType.values());
        cbType.addCustomValueSetListener(event -> {
            switch (event.getSource().getValue()) {
                case BOOLEAN:
                case ENUM_TIME_ZONE:
                    tfValue.setVisible(false);
                    cbValue.setVisible(true);
                    break;
                case IMG:
                case DATE:
                case TEXT:
                case NUMERIC:
                default:
                    tfValue.setVisible(true);
                    cbValue.setVisible(false);
                    break;
            }
        });
    }

    @Override
    public void restore(ParamDto data) {
        tfCode.setValue((double) data.getCode());
        tfName.setValue(data.getName());
        if (tfValue.isVisible()) tfValue.setValue(data.getValue());
        if (cbValue.isVisible()) cbValue.setValue(Boolean.parseBoolean(data.getValue()));
        cbType.setValue(data.getType());
        taDescription.setValue(data.getDescription());
        chOnlyRoot.setValue(data.getChangeRoot());
    }

    @Override
    public void visualize() {
        tfCode.setReadOnly(true);
        tfName.setReadOnly(true);
        tfValue.setReadOnly(true);
        cbValue.setReadOnly(true);
        cbType.setReadOnly(true);
        taDescription.setReadOnly(true);
        chOnlyRoot.setEnabled(true);
    }

    @Override
    public boolean validField() {
        Binder<ParamDto> binder = new Binder<>(ParamDto.class);
        binder.forField(tfCode)
                .asRequired("Code is required")
                .bind("code");
        binder.forField(tfName)
                .asRequired("Name is required")
                .bind("name");
        binder.forField(tfValue)
                .asRequired("Value is required")
                .bind("value");
        binder.forField(tfValue)
                .asRequired("Type is required")
                .bind("type");
        return !tfCode.isInvalid() && !tfName.isInvalid() && (!tfValue.isInvalid() || !tfValue.isVisible()) && (!cbValue.isInvalid() || !tfValue.isVisible()) && !cbType.isInvalid();
    }

    @Override
    public ParamDto collectData(ParamDto model) {
        model.setCode(tfCode.getValue().intValue());
        model.setName(tfName.getValue());
        if (tfValue.isVisible()) model.setValue(tfValue.getValue());
        if (cbValue.isVisible()) model.setValue(cbValue.getValue().toString());
        model.setType(cbType.getValue());
        model.setDescription(taDescription.getValue());
        model.setChangeRoot(chOnlyRoot.getValue());
        return model;
    }

    @Override
    public void security() {

    }

    public interface ParameterFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
