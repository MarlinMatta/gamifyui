package edu.uapa.ui.gamify.views.gamifies;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.ExamDto;
import edu.utesa.lib.models.dtos.school.ProblemDto;
import edu.utesa.lib.models.dtos.school.SubjectDto;
import edu.utesa.lib.models.dtos.school.TopicDto;
import edu.utesa.lib.models.enums.ExamDifficulty;
import edu.utesa.lib.utils.DateUtils;

import java.text.ParseException;
import java.util.List;

/**
 * A Designer generated component for the exam-form-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("exam-form-design")
@HtmlImport("src/views/gamifies/exam-form-design.html")
public class ExamFormDesign extends PolymerTemplate<ExamFormDesign.ExamFormDesignModel> implements FormStructure<ExamDto> {

    @Id("cbDifficulty")
    private ComboBox<ExamDifficulty> cbDifficulty;
    @Id("cbSubject")
    private ComboBox<SubjectDto> cbSubject;
    @Id("cbTopic")
    private ComboBox<TopicDto> cbTopic;
    @Id("cbProblemQuantity")
    private ComboBox<String> cbProblemQuantity;
    @Id("dpFromDate")
    private DatePicker dpFromDate;
    @Id("dpToDate")
    private DatePicker dpToDate;
    @Id("btGenerate")
    private Button btGenerate;
    @Id("gdProblems")
    private Grid<ProblemDto> gdProblems;
    @Id("tfPoints")
    private TextField tfPoints;

    private SubjectDto subject;
    private TopicDto topic;

    private String[] problemQuantity = {"20","25","30","35","40"};


    /**
     * Creates a new ExamFormDesign.
     */
    public ExamFormDesign() {
        cbDifficulty.setLabel(Captions.DIFFICULTY);
        cbSubject.setLabel(Captions.SUBJECT);
        cbTopic.setLabel(Captions.TOPIC);
        cbProblemQuantity.setLabel(Captions.PROBLEM_QUANTITY);
        dpFromDate.setLabel(Captions.FROM_DATE);
        dpToDate.setLabel(Captions.TO_DATE);
        tfPoints.setLabel(Captions.POINTS);
        btGenerate.setText(Captions.BUTTON_GENERATE);

        cbProblemQuantity.setItems(problemQuantity);

        buildGrid();

        subject = new SubjectDto();
        topic = new TopicDto();
    }

    public void fillGrid(List<ProblemDto> problems){
        gdProblems.setItems(problems);
    }

    public void buildGrid(){
        gdProblems.addColumn(ProblemDto::theTopicName).setHeader(Captions.GRID_COLUMN_TOPIC);
        gdProblems.addColumn(ProblemDto::getExamDifficulty).setHeader(Captions.GRID_COLUMN_DIFFICULTY);
        gdProblems.addColumn(ProblemDto::getQuestion).setHeader(Captions.GRID_COLUMN_QUESTION);
    }

    @Override
    public void restore(ExamDto data) {
        subject = data.getSubjectDto();
        topic = data.getTopicDto();

        cbDifficulty.setValue(data.getExamDifficulty());
        cbSubject.setValue(subject);
        cbTopic.setValue(topic);
        cbProblemQuantity.setValue(String.valueOf(data.getProblemQuantity()));
        dpFromDate.setValue(DateUtils.asLocalDate(data.getFromDate()));
        dpToDate.setValue(DateUtils.asLocalDate(data.getToDate()));
        tfPoints.setValue(String.valueOf(data.getPoints()));
    }

    @Override
    public void visualize() {
        cbDifficulty.setReadOnly(true);
        cbSubject.setReadOnly(true);
        cbTopic.setReadOnly(true);
        cbProblemQuantity.setReadOnly(true);
        dpFromDate.setReadOnly(true);
        dpToDate.setReadOnly(true);
        tfPoints.setReadOnly(true);
        btGenerate.setEnabled(false);
    }

    @Override
    public boolean validField() {
        if (cbDifficulty.isInvalid())
            return false;
        if (cbSubject.isInvalid())
            return false;
        if (cbTopic.isInvalid())
            return false;
        if (cbProblemQuantity.isInvalid())
            return false;
        if (dpFromDate.isInvalid())
            return false;
        if (dpToDate.isInvalid())
            return false;
        if (tfPoints.isInvalid())
            return false;
        return true;
    }

    @Override
    public ExamDto collectData(ExamDto model) {
        subject = cbSubject.getValue();
        topic = cbTopic.getValue();

//        model.setTeacherDto(Tools.);
        model.setSubjectDto(subject);
        model.setTopicDto(topic);
        model.setExamDifficulty(cbDifficulty.getValue());
        model.setProblemQuantity(Integer.parseInt(cbProblemQuantity.getValue()));
        model.setFromDate(DateUtils.asDate(dpFromDate.getValue()));
        model.setToDate(DateUtils.asDate(dpToDate.getValue()));
        model.setPoints(Integer.parseInt(tfPoints.getValue()));

        return null;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between ExamFormDesign and exam-form-design
     */
    public interface ExamFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
