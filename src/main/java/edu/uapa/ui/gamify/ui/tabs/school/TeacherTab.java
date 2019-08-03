package edu.uapa.ui.gamify.ui.tabs.school;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.teacher.TeacherRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.teacher.TeacherForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.TeacherDto;

import java.util.List;

public class TeacherTab extends AbstractTabWithGrid<TeacherDto> {

    public TeacherTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(TeacherDto::getFullName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(TeacherDto::getFullAddress).setHeader(Captions.GRID_COLUMN_ADDRESS);
        getGrid().addColumn(TeacherDto::getSchoolName).setHeader(Captions.GRID_COLUMN_SCHOOL);
        getGrid().addColumn(TeacherDto::getGradeName).setHeader(Captions.GRID_COLUMN_GRADE);
        getGrid().addColumn(TeacherDto::getUserNickname).setHeader(Captions.GRID_COLUMN_USER);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return TeacherRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<TeacherDto> getData(Long page, Long size, String searchValue) {
        return TeacherRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new TeacherForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(TeacherDto data) {
        new TeacherForm(data, true);
    }

    @Override
    protected void edit(TeacherDto data) {
        new TeacherForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        TeacherRequests.getInstance().delete(id);
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
