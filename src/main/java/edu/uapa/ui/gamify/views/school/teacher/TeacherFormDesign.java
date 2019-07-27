package edu.uapa.ui.gamify.views.school.teacher;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.location.AddressDto;
import edu.utesa.lib.models.dtos.person.PersonDto;
import edu.utesa.lib.models.dtos.school.GradeDto;
import edu.utesa.lib.models.dtos.school.SchoolDto;
import edu.utesa.lib.models.dtos.school.SubjectDto;
import edu.utesa.lib.models.dtos.school.TeacherDto;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.models.enums.Language;
import edu.utesa.lib.models.enums.person.Gender;
import edu.utesa.lib.utils.DateUtils;

/**
 * A Designer generated component for the teacher-form-design.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("teacher-form-design")
@HtmlImport("src/views/school/teacher-form-design.html")
public class TeacherFormDesign extends PolymerTemplate<TeacherFormDesign.TeacherFormDesignModel> implements FormStructure<TeacherDto> {

    @Id("tfName")
    private TextField tfName;
    @Id("dpDateBirth")
    private DatePicker dpDateBirth;
    @Id("tfLastName")
    private TextField tfLastName;
    @Id("cbGender")
    private ComboBox<Gender> cbGender;
    @Id("cbSchool")
    private ComboBox<SchoolDto> cbSchool;
    @Id("cbSubject")
    private ComboBox<SubjectDto> cbSubject;
    @Id("cbGrade")
    private ComboBox<GradeDto> cbGrade;
    @Id("cbCity")
    private ComboBox<String> cbCity;
    @Id("tfSector")
    private TextField tfSector;
    @Id("tfAddress")
    private TextField tfAddress;
    @Id("tfZipCode")
    private TextField tfZipCode;
    @Id("tfUserName")
    private TextField tfUserName;
    @Id("tfPassword")
    private TextField tfPassword;
    @Id("efMail")
    private EmailField efMail;

    private AddressDto address;
    private PersonDto person;
    private UserDto user;

    /**
     * Creates a new TeacherFormDesign.
     */
    public TeacherFormDesign() {
        // You can initialise any data required for the connected UI components here.
        tfName.setLabel(Captions.NAME);
        tfLastName.setLabel(Captions.LAST_NAME);
        dpDateBirth.setLabel(Captions.BIRTHDAY);
        cbGender.setLabel(Captions.GENDER);
        cbSchool.setLabel(Captions.SCHOOL);
        cbGrade.setLabel(Captions.GRADE);
        cbSubject.setLabel(Captions.SUBJECT);
        cbCity.setLabel(Captions.CITY);
        tfSector.setLabel(Captions.SECTOR);
        tfZipCode.setLabel(Captions.ZIP_CODE);
        tfAddress.setLabel(Captions.ADDRESS);
        tfUserName.setLabel(Captions.USER_NAME);
        tfPassword.setLabel(Captions.PASSWORD);
        efMail.setLabel(Captions.MAIL);

        address = new AddressDto();
        person = new PersonDto();
        user = new UserDto();
    }


    @Override
    public void restore(TeacherDto data) {
        address = data.getAddressDto();
        tfName.setValue(data.getPersonDto().getFirstNames());
        tfLastName.setValue(data.getPersonDto().getLastNames());
        dpDateBirth.setValue(DateUtils.asLocalDate(data.getPersonDto().getBirthDay()));
        cbGender.setValue(data.getPersonDto().getGender());
        cbGrade.setValue(data.getGradeDto());
        cbSchool.setValue(data.getSchoolDto());
        // cbSubject.setValue(data.getSubjectDto ()); o se por que no funciona
        cbCity.setValue(data.getAddressDto().getCity());
        tfSector.setValue(data.getAddressDto().getSector());
        tfZipCode.setValue(data.getAddressDto().getZipCode());
        tfAddress.setValue(data.getAddressDto().getAddress());
        tfUserName.setValue(data.getUserDto().getNickName());
        tfPassword.setValue(data.getUserDto().getPassword());
        efMail.setValue(data.getUserDto().getMail());

    }

    @Override
    public void visualize() {
        tfName.setReadOnly(true);
        tfLastName.setReadOnly(true);
        dpDateBirth.setReadOnly(true);
        cbGender.setReadOnly(true);
        cbGrade.setReadOnly(true);
        cbSchool.setReadOnly(true);
        cbSubject.setReadOnly(true);
        cbCity.setReadOnly(true);
        tfSector.setReadOnly(true);
        tfZipCode.setReadOnly(true);
        tfAddress.setReadOnly(true);
        tfUserName.setReadOnly(true);
        tfPassword.setReadOnly(true);
        efMail.setReadOnly(true);

    }

    @Override
    public boolean validField() {
        return !tfName.isInvalid() && !tfLastName.isInvalid() && !dpDateBirth.isInvalid() && !cbGender.isInvalid() && !cbSubject.isInvalid() && !cbGrade.isInvalid() && !cbSchool.isInvalid() && !cbCity.isInvalid() && !tfSector.isInvalid() && !tfZipCode.isInvalid() && !tfAddress.isInvalid() && !tfUserName.isInvalid() && !tfPassword.isInvalid() && !efMail.isInvalid();
    }

    @Override
    public TeacherDto collectData(TeacherDto model) {
        address.setName(tfName.getValue());
        address.setCity(cbCity.getValue());
        address.setSector(tfSector.getValue());
        address.setZipCode(tfZipCode.getValue());
        model.setAddressDto(address);

        person.setFirstNames(tfName.getValue());
        person.setLastNames(tfLastName.getValue());
        person.setGender(cbGender.getValue());
        person.setAddressDto(address);
        person.setBirthDay(DateUtils.asDate(dpDateBirth.getValue()));
        //  model.setSubjectDto())
        model.setPersonDto(person);

        user.setAdmin(true);
        user.setLanguage(Language.SPANISH);
        return model;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between TeacherFormDesign and teacher-form-design.html
     */
    public interface TeacherFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
