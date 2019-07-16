package edu.uapa.ui.gamify.views.school.grade;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.GradeDto;
import edu.utesa.lib.models.dtos.school.SchoolDto;

import java.util.List;

/**
 * A Designer generated component for the grade-form-design.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("grade-form-design")
@HtmlImport("src/views/school/grade-form-design.html")
public class GradeFormDesign extends PolymerTemplate<GradeFormDesign.GradeFormDesignModel> implements FormStructure<GradeDto> {

    @Id("cbSchool")
    private ComboBox<SchoolDto> cbSchool;
    @Id("tfName")
    private TextField tfName;
    @Id("tfDescription")
    private TextField tfDescription;

    /**
     * Creates a new GradeFormDesign.
     */
    public GradeFormDesign() {
        cbSchool.setLabel(Captions.SCHOOL);
        tfName.setLabel(Captions.NAME);
        tfDescription.setLabel(Captions.DESCRIPTION);
    }

    public void fillSchool(List<SchoolDto> items) {
        cbSchool.setItems(items);
    }

    @Override
    public void restore(GradeDto data) {
        cbSchool.setValue(data.getSchoolDto());
        tfName.setValue(data.getName());
        tfDescription.setValue(data.getDescription());
    }

    @Override
    public void visualize() {
        cbSchool.setReadOnly(true);
        tfName.setReadOnly(true);
        tfDescription.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        return !cbSchool.isInvalid() && !tfName.isInvalid();
    }

    @Override
    public GradeDto collectData(GradeDto model) {
        model.setSchoolDto(cbSchool.getValue());
        model.setName(tfName.getValue());
        model.setDescription(tfDescription.getValue());
        return model;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between GradeFormDesign and grade-form-design.html
     */
    public interface GradeFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
