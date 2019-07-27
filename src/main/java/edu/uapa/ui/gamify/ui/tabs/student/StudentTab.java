package edu.uapa.ui.gamify.ui.tabs.student;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.student.StudentRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.student.StudentForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.StudentDto;

import java.util.List;

public class StudentTab extends AbstractTabWithGrid<StudentDto> {

    public StudentTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(StudentDto::getFullName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(StudentDto::getFullAddress).setHeader(Captions.GRID_COLUMN_ADDRESS);
        getGrid().addColumn(StudentDto::getSchoolName).setHeader(Captions.GRID_COLUMN_SCHOOL);
        getGrid().addColumn(StudentDto::getGradeName).setHeader(Captions.GRID_COLUMN_GRADE);
        getGrid().addColumn(StudentDto::getUserNickname).setHeader(Captions.GRID_COLUMN_USER);
        addCRUDActionGrid();
    }

    @Override
    protected Long getTotalRows(String searchValue) {
        return StudentRequests.getInstance().count(searchValue);
    }

    @Override
    protected List<StudentDto> getData(Long page, Long size, String searchValue) {
        return StudentRequests.getInstance().get(page.intValue(), size.intValue(), searchValue);
    }

    @Override
    protected void _new() {
        new StudentForm().addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void view(StudentDto data) {
        new StudentForm(data, true);
    }

    @Override
    protected void edit(StudentDto data) {
        new StudentForm(data, false).addOpenedChangeListener(event -> refresh());
    }

    @Override
    protected void delete(Long id) {
        StudentRequests.getInstance().delete(id);
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
