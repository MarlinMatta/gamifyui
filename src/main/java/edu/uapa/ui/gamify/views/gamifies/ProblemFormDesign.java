package edu.uapa.ui.gamify.views.gamifies;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.AnswerDto;
import edu.utesa.lib.models.dtos.school.ProblemDto;
import edu.utesa.lib.models.dtos.school.TeacherDto;
import edu.utesa.lib.models.dtos.school.TopicDto;
import edu.utesa.lib.models.enums.ExamDifficulty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A Designer generated component for the problem-form-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("problem-form-design")
@HtmlImport("src/views/gamifies/problem-form-design.html")
public class ProblemFormDesign extends PolymerTemplate<ProblemFormDesign.ProblemFormDesignModel> implements FormStructure<ProblemDto> {

    @Id("cbTeacher")
    private ComboBox<TeacherDto> cbTeacher;
    @Id("cbTopic")
    private ComboBox<TopicDto> cbTopic;
    @Id("cbDifficulty")
    private ComboBox<ExamDifficulty> cbDifficulty;
    @Id("taQuestion")
    private TextArea taQuestion;
    @Id("tfAnswer01")
    private TextField tfAnswer01;
    @Id("cbCorrect01")
    private Checkbox cbCorrect01;
    @Id("tfAnswer02")
    private TextField tfAnswer02;
    @Id("cbCorrect02")
    private Checkbox cbCorrect02;
    @Id("tfAnswer03")
    private TextField tfAnswer03;
    @Id("cbCorrect03")
    private Checkbox cbCorrect03;
    @Id("tfAnswer04")
    private TextField tfAnswer04;
    @Id("cbCorrect04")
    private Checkbox cbCorrect04;

    TeacherDto teacher;
    TopicDto topic;

    /**
     * Creates a new ProblemFormDesign.
     */
    public ProblemFormDesign() {
        cbTeacher.setLabel(Captions.TEACHER);
        cbTopic.setLabel(Captions.TOPIC);
        cbDifficulty.setLabel(Captions.DIFFICULTY);
        taQuestion.setLabel(Captions.QUESTION);

        teacher = new TeacherDto();
        topic = new TopicDto();
    }

    public void fillDifficulty() {
        cbDifficulty.setItems(ExamDifficulty.values());
        cbDifficulty.setItemLabelGenerator(ExamDifficulty::name);
        cbDifficulty.setValue(ExamDifficulty.BASIC);
    }

    public void fillTeacher(List<TeacherDto> items) {
        cbTeacher.setItemLabelGenerator(TeacherDto::theFullName);
        cbTeacher.setItems(items);
        cbTeacher.setValue(items.get(0));
    }

    public void fillTopic(List<TopicDto> items) {
        cbTopic.setItemLabelGenerator(TopicDto::getName);
        cbTopic.setItems(items);
        cbTopic.setValue(items.get(0));
    }

    @Override
    public void restore(ProblemDto data) {
        teacher = data.getTeacherDto();
        topic = data.getTopicDto();

        cbTeacher.setValue(teacher);
        cbTopic.setValue(topic);
        cbDifficulty.setValue(data.getExamDifficulty());
        taQuestion.setValue(data.getQuestion());
        tfAnswer01.setValue(data.getCorrectAnswer());
        cbCorrect01.setValue(true);
        tfAnswer02.setValue(data.getIncorrectAnswer01());
        cbCorrect02.setValue(false);
        tfAnswer03.setValue(data.getIncorrectAnswer02());
        cbCorrect03.setValue(false);
        tfAnswer04.setValue(data.getIncorrectAnswer03());
        cbCorrect04.setValue(false);
    }

    @Override
    public void visualize() {
        cbTeacher.setReadOnly(true);
        cbTopic.setReadOnly(true);
        cbDifficulty.setReadOnly(true);
        taQuestion.setReadOnly(true);
        tfAnswer01.setReadOnly(true);
        cbCorrect01.setReadOnly(true);
        tfAnswer02.setReadOnly(true);
        cbCorrect02.setReadOnly(true);
        tfAnswer03.setReadOnly(true);
        cbCorrect03.setReadOnly(true);
        tfAnswer04.setReadOnly(true);
        cbCorrect04.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        if (cbTeacher.isInvalid())
            return false;
        if (cbTopic.isInvalid())
            return false;
        if (cbDifficulty.isInvalid())
            return false;
        if (taQuestion.isInvalid())
            return false;
        if (tfAnswer01.isInvalid())
            return false;
        if (cbCorrect01.isIndeterminate())
            return false;
        if (tfAnswer02.isInvalid())
            return false;
        if (cbCorrect02.isIndeterminate())
            return false;
        if (tfAnswer03.isInvalid())
            return false;
        if (cbCorrect03.isIndeterminate())
            return false;
        if (tfAnswer04.isInvalid())
            return false;
        if (cbCorrect04.isIndeterminate())
            return false;
        return true;
    }

    @Override
    public ProblemDto collectData(ProblemDto model) {
        teacher = cbTeacher.getValue();
        topic = cbTopic.getValue();

        model.setExamDifficulty(cbDifficulty.getValue());
        model.setQuestion(taQuestion.getValue());

        if (cbCorrect01.getValue()) {
            model.setCorrectAnswer(tfAnswer01.getValue());
            model.setIncorrectAnswer01(tfAnswer02.getValue());
            model.setIncorrectAnswer02(tfAnswer03.getValue());
            model.setIncorrectAnswer03(tfAnswer04.getValue());
        } else if (cbCorrect02.getValue()) {
            model.setCorrectAnswer(tfAnswer02.getValue());
            model.setIncorrectAnswer01(tfAnswer01.getValue());
            model.setIncorrectAnswer02(tfAnswer03.getValue());
            model.setIncorrectAnswer03(tfAnswer04.getValue());
        } else if (cbCorrect03.getValue()) {
            model.setCorrectAnswer(tfAnswer03.getValue());
            model.setIncorrectAnswer01(tfAnswer01.getValue());
            model.setIncorrectAnswer02(tfAnswer02.getValue());
            model.setIncorrectAnswer03(tfAnswer04.getValue());
        } else if (cbCorrect04.getValue()) {
            model.setCorrectAnswer(tfAnswer04.getValue());
            model.setIncorrectAnswer01(tfAnswer01.getValue());
            model.setIncorrectAnswer02(tfAnswer02.getValue());
            model.setIncorrectAnswer03(tfAnswer03.getValue());
        }

        model.setTeacherDto(teacher);
        model.setTopicDto(topic);

        return model;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between ProblemFormDesign and problem-form-design
     */
    public interface ProblemFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
