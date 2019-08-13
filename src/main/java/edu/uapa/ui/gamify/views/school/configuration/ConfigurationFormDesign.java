package edu.uapa.ui.gamify.views.school.configuration;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.utesa.lib.models.dtos.configurations.ConfigurationDto;
import edu.utesa.lib.models.enums.GameDifficulty;
import edu.utesa.lib.models.enums.GameDifficulty;
import edu.utesa.lib.models.enums.GameMode;

import java.util.List;

/**
 * A Designer generated component for the configuration-form-design.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("configuration-form-design")
@HtmlImport("src/views/school/configuration/configuration-form-design.html")
public class ConfigurationFormDesign extends PolymerTemplate<ConfigurationFormDesign.ConfigurationFormDesignModel> implements FormStructure<ConfigurationDto> {

    @Id("cbdifficulty")
    private ComboBox<GameDifficulty> cbdifficulty;
    @Id("cbquestions")
    private ComboBox<Integer> cbquestions;
    @Id("cbmode")
    private ComboBox<GameMode> cbmode;


    /**
     * Creates a new ConfigurationFormDesign.
     */
    public ConfigurationFormDesign() {
        cbdifficulty.setLabel("Difficulty");
        cbdifficulty.setRequired(true);

        cbquestions.setLabel("Questions");
        cbquestions.setRequired(true);

        cbmode.setLabel("Mode");
        cbmode.setRequired(true);
    }

    public void fillDifficulty() {
        cbdifficulty.setItems(GameDifficulty.values());
    }

    public void fillQuestion(List<Integer> items) {
        cbquestions.setItems(items);
    }

    public void fillMode() {
        cbmode.setItems(GameMode.values());
    }

    public void activeMode(boolean active) {
        cbmode.setVisible(active);
    }

    @Override
    public void restore(ConfigurationDto data) {
        cbdifficulty.setValue(data.getDifficulty());
        cbquestions.setValue(data.getQuestions());
        cbmode.setValue(data.getMode());
    }

    @Override
    public void visualize() {
        cbdifficulty.setReadOnly(true);
        cbquestions.setReadOnly(true);
        cbmode.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        if (cbdifficulty.getValue() == null)
            return false;
        else if (cbquestions.getValue() == null)
            return false;
        else if (cbmode.getValue() == null && cbmode.isVisible())
            return false;
        return true;
    }

    @Override
    public ConfigurationDto collectData(ConfigurationDto model) {
        model.setDifficulty(cbdifficulty.getValue());
        model.setQuestions(cbquestions.getValue());
        model.setMode(cbmode.getValue());
        return model;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between ConfigurationFormDesign and configuration-form-design.html
     */
    public interface ConfigurationFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
