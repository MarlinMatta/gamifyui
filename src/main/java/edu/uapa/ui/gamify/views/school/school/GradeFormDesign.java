package edu.uapa.ui.gamify.views.school.school;

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
 * A Designer generated component for the school-form-design template.
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

    private SchoolDto school;

    /**
     * Creates a new SchoolFormDesign.
     */
    public GradeFormDesign() {
        // You can initialise any data required for the connected UI components here.
        cbSchool.setLabel(Captions.SCHOOL);
        tfName.setLabel(Captions.NAME);
        tfDescription.setLabel(Captions.DESCRIPTION);

        school = new SchoolDto();
    }

    public void fillSchool(List<SchoolDto> items) {
        cbSchool.setItemLabelGenerator(SchoolDto::getName);
        cbSchool.setItems(items);
        cbSchool.setValue(items.get(0));
    }

    @Override
    public void restore(GradeDto data) {
        school = data.getSchoolDto();

        cbSchool.setValue(data.getSchoolDto());
        tfName.setValue(data.getName());
        tfDescription.setValue(data.getDescription() == null ? "" : data.getDescription());
    }

    @Override
    public void visualize() {
        cbSchool.setReadOnly(true);
        tfName.setReadOnly(true);
        tfDescription.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        if (tfName.isInvalid())
            return false;
        if (cbSchool.isInvalid())
            return false;
        if (tfDescription.isInvalid())
            return false;
        return true;
    }

    @Override
    public GradeDto collectData(GradeDto model) {
        school = cbSchool.getValue();

        model.setName(tfName.getValue());
        model.setDescription(tfDescription.getValue());
        model.setSchoolDto(school);
        return model;
    }


    @Override
    public void security() {

    }

    /**
     * This model binds properties between SchoolFormDesign and school-form-design
     */
    public interface GradeFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
