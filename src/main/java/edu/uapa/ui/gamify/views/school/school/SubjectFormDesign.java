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
import edu.utesa.lib.models.dtos.location.AddressDto;
import edu.utesa.lib.models.dtos.school.GradeDto;
import edu.utesa.lib.models.dtos.school.SubjectDto;

import java.util.List;

/**
 * A Designer generated component for the school-form-design.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("subject-form-design")
@HtmlImport("src/views/school/subject-form-design.html")
public class SubjectFormDesign extends PolymerTemplate<SubjectFormDesign.SubjectFormDesignModel> implements FormStructure<SubjectDto> {

    @Id("cbGrade")
    private ComboBox<GradeDto> cbGrade;
    @Id("tfName")
    private TextField tfName;
    @Id("tfDescription")
    private TextField tfDescription;

    private GradeDto grade;
    /**
     * Creates a new SchoolFormDesign.
     */
    public SubjectFormDesign() {
        // You can initialise any data required for the connected UI components here.
        cbGrade.setLabel(Captions.GRADE);
        tfName.setLabel(Captions.NAME);
        tfDescription.setLabel(Captions.DESCRIPTION);

        grade = new GradeDto();
    }

    public void fillGrade(List<GradeDto> items) {
        cbGrade.setItemLabelGenerator(GradeDto::getName);
        cbGrade.setItems(items);
    }

    @Override
    public void restore(SubjectDto data) {
        grade = data.getGradeDto();

        cbGrade.setValue(data.getGradeDto());
        tfName.setValue(data.getName());
        tfDescription.setValue(data.getDescription() == null ? "" : data.getDescription());
    }

    @Override
    public void visualize() {
        cbGrade.setReadOnly(true);
        tfName.setReadOnly(true);
        tfDescription.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        if (tfName.isInvalid())
            return false;
        if (cbGrade.isInvalid())
            return false;
        if (tfDescription.isInvalid())
            return false;
        return true;
    }

    @Override
    public SubjectDto collectData(SubjectDto model) {
        grade = cbGrade.getValue();

        model.setName(tfName.getValue());
        model.setDescription(tfDescription.getValue());
        model.setGradeDto(grade);
        return model;
    }


    @Override
    public void security() {

    }

    /**
     * This model binds properties between SchoolFormDesign and school-form-design.html
     */
    public interface SubjectFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
