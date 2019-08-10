package edu.uapa.ui.gamify.ui.tabs.gamifies;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.gamifies.ExamRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.gamifies.ExamForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.ExamDto;

import java.util.List;

public class ExamTab extends AbstractTabWithGrid<ExamDto> {

    public ExamTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(ExamDto::theTeacherName).setHeader(Captions.GRID_COLUMN_TEACHER);
        getGrid().addColumn(ExamDto::theTopicName).setHeader(Captions.GRID_COLUMN_TOPIC);
        getGrid().addColumn(ExamDto::getExamDifficulty).setHeader(Captions.GRID_COLUMN_DIFFICULTY);
        getGrid().addColumn(ExamDto::getPoints).setHeader(Captions.GRID_COLUMN_POINTS);
        getGrid().addColumn(ExamDto::getToDate).setHeader(Captions.GRID_COLUMN_TO_DATE);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return ExamRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<ExamDto> getData(Long page, Long size, String searchValue) {
        return ExamRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new ExamForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(ExamDto data) {
        new ExamForm(data, true);
    }

    @Override
    protected void edit(ExamDto data) {
        new ExamForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        ExamRequests.getInstance().delete(id);
        refresh();
    }

    @Override
    protected void cantAdd(Component component) {
        component.setVisible(true);
    }

    @Override
    protected void cantView(Component component) {
        component.setVisible(true);
    }

    @Override
    protected void cantEdit(Component component) {
        component.setVisible(true);
    }

    @Override
    protected void cantDelete(Component component) {
        component.setVisible(false);
    }
}
