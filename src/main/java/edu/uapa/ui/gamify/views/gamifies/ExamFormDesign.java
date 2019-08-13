package edu.uapa.ui.gamify.views.gamifies;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.requests.gamifies.ProblemRequests;
import edu.uapa.ui.gamify.requests.gamifies.TopicRequests;
import edu.uapa.ui.gamify.requests.school.SubjectRequests;
import edu.uapa.ui.gamify.requests.school.TeacherRequests;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.*;
import edu.utesa.lib.models.enums.GameDifficulty;
import edu.utesa.lib.models.enums.GameDifficulty;
import edu.utesa.lib.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private ComboBox<GameDifficulty> cbDifficulty;
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

    private TeacherDto teacher;
    private SubjectDto subject;
    private TopicDto topic;

    private String[] problemQuantity = {"5", "10", "15", "20", "25", "30", "35", "40"};


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
        cbProblemQuantity.setValue(problemQuantity[0]);

        subject = new SubjectDto();
        topic = new TopicDto();
        teacher = TeacherRequests.getInstance().refreshByUser(Tools.getUserId());

        setClickListeners();
        fillDifficulty();
        fillSubject();
        buildGrid();
    }

    private void setClickListeners() {
        btGenerate.addClickListener(event -> {
            generateExam();
        });

        cbSubject.addValueChangeListener(event -> {
            fillTopic(TopicRequests.getInstance().getBySubject(event.getValue().getId().toString()));
        });
    }

    public void fillDifficulty() {
        cbDifficulty.setItems(GameDifficulty.values());
        cbDifficulty.setItemLabelGenerator(GameDifficulty::name);
        cbDifficulty.setValue(GameDifficulty.BASIC   );
    }

    public void fillSubject() {
        List<SubjectDto> items = SubjectRequests.getInstance().getByTeacher(teacher.getId().toString());
        cbSubject.setItemLabelGenerator(SubjectDto::getName);
        cbSubject.setItems(items);
        cbSubject.setValue(items.get(0));
    }

    public void fillTopic(List<TopicDto> items) {
        cbTopic.setItemLabelGenerator(TopicDto::getName);
        cbTopic.setItems(items);
        cbTopic.setValue(items.get(0));
    }

    public void generateExam() {
        final GameDifficulty difficulty = cbDifficulty.getValue();
        final int size = Integer.parseInt(cbProblemQuantity.getValue());
        final List<ProblemDto> problems = ProblemRequests.getInstance().getPractice(difficulty, size);
        fillGrid(problems);
    }

    public void fillGrid(List<ProblemDto> problems) {
        gdProblems.setItems(problems);
    }

    public void buildGrid() {
        gdProblems.addColumn(ProblemDto::theTopicName).setHeader(Captions.GRID_COLUMN_TOPIC);
        gdProblems.addColumn(ProblemDto::getGameDifficulty).setHeader(Captions.GRID_COLUMN_DIFFICULTY);
        gdProblems.addColumn(ProblemDto::getQuestion).setHeader(Captions.GRID_COLUMN_QUESTION);
    }

    @Override
    public void restore(ExamDto data) {
        subject = data.getSubjectDto();
        topic = data.getTopicDto();

        cbDifficulty.setValue(data.getGameDifficulty());
        cbSubject.setValue(subject);
        cbTopic.setValue(topic);
        cbProblemQuantity.setValue(String.valueOf(data.getProblemQuantity()));
        try {
            dpFromDate.setValue(DateUtils.asLocalDate(new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").parse(data.getFromDate())));
            dpToDate.setValue(DateUtils.asLocalDate(new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").parse(data.getToDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tfPoints.setValue(String.valueOf(data.getPoints()));

        fillGrid(new ArrayList<>(data.getProblems()));
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

        model.setTeacherDto(teacher);
        model.setSubjectDto(subject);
        model.setTopicDto(topic);
        model.setGameDifficulty(cbDifficulty.getValue());
        model.setProblemQuantity(Integer.parseInt(cbProblemQuantity.getValue()));
        model.setFromDate(dpFromDate.getValue().toString());
        model.setToDate(dpToDate.getValue().toString());
        model.setPoints(Integer.parseInt(tfPoints.getValue()));
        model.setProblems(gdProblems.getDataProvider().fetch(new Query<>()).collect(Collectors.toSet()));

        return model;
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
