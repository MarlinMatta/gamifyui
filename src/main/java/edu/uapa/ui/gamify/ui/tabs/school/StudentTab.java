package edu.uapa.ui.gamify.ui.tabs.school;

import com.vaadin.flow.component.Component;
import edu.uapa.ui.gamify.requests.school.StudentRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractTabWithGrid;
import edu.uapa.ui.gamify.ui.form.school.StudentForm;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.school.StudentDto;

import java.util.List;

public class StudentTab extends AbstractTabWithGrid<StudentDto> {

    public StudentTab() {
    }

    @Override
    protected void setLanguage() {
        getGrid().addColumn(StudentDto::theFullName).setHeader(Captions.GRID_COLUMN_NAME);
        getGrid().addColumn(StudentDto::theFullAddress).setHeader(Captions.GRID_COLUMN_ADDRESS);
        getGrid().addColumn(StudentDto::theSchoolName).setHeader(Captions.GRID_COLUMN_SCHOOL);
        getGrid().addColumn(StudentDto::theGradeName).setHeader(Captions.GRID_COLUMN_GRADE);
        getGrid().addColumn(StudentDto::theUserNickname).setHeader(Captions.GRID_COLUMN_USER);
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
        component.setVisible(!getLoginManager().hasPermission(0));
    }

    @Override
    protected void cantView(Component component) {
        component.setVisible(true);
    }

    @Override
    protected void cantEdit(Component component) {
        component.setVisible(!getLoginManager().hasPermission(0));
    }

    @Override
    protected void cantDelete(Component component) {
        component.setVisible(!getLoginManager().hasPermission(0));
    }
}
