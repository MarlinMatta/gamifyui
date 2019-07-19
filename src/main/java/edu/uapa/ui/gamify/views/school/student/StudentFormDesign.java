package edu.uapa.ui.gamify.views.school.student;

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
import edu.utesa.lib.models.dtos.school.StudentDto;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.models.enums.Language;
import edu.utesa.lib.models.enums.person.Gender;
import edu.utesa.lib.utils.DateUtils;

/**
 * A Designer generated component for the student-form-design.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("student-form-design")
@HtmlImport("src/views/school/student-form-design.html")
public class StudentFormDesign extends PolymerTemplate<StudentFormDesign.StudentFormDesignModel> implements FormStructure<StudentDto> {

    @Id("tfName")
    private TextField tfName;
    @Id("tfLastName")
    private TextField tfLastName;
    @Id("cbGender")
    private ComboBox<Gender> cbGender;
    @Id("cbGrade")
    private ComboBox<GradeDto> cbGrade;
    @Id("cbSchool")
    private ComboBox<SchoolDto> cbSchool;
    @Id("cbCity")
    private ComboBox<String> cbCity;
    @Id("tfSector")
    private TextField tfSector;
    @Id("tfZipCode")
    private TextField tfZipCode;
    @Id("tfAddress")
    private TextField tfAddress;
    @Id("tfUserName")
    private TextField tfUserName;
    @Id("tfPassword")
    private TextField tfPassword;

    private AddressDto address;
    private PersonDto person;
    private UserDto user;
    @Id("dpDateBirth")
    private DatePicker dpDateBirth;
    @Id("efMail")
    private EmailField efMail;


    /**
     * Creates a new StudentFormDesign.
     */
    public StudentFormDesign() {

        // You can initialise any data required for the connected UI components here.
        tfName.setLabel(Captions.NAME);
        tfLastName.setLabel(Captions.LAST_NAME);
        dpDateBirth.setLabel(Captions.DATE_BIRTH);
        cbGender.setLabel(Captions.GENDER);
        cbSchool.setLabel(Captions.SCHOOL);
        cbGrade.setLabel(Captions.GRADE);
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
    public void restore(StudentDto data) {
        address = data.getAddressDto();
        tfName.setValue(data.getPersonDto().getFirstNames());
        tfLastName.setValue(data.getPersonDto().getLastNames());
        dpDateBirth.setValue(DateUtils.asLocalDate(data.getPersonDto().getBirthDay()));
        cbGender.setValue(data.getPersonDto().getGender());
        cbGrade.setValue(data.getGradeDto());
        cbSchool.setValue(data.getSchoolDto());
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
        return !tfName.isInvalid() && !tfLastName.isInvalid() && !dpDateBirth.isInvalid() && !cbGender.isInvalid() && !cbGrade.isInvalid() && !cbSchool.isInvalid() && !cbCity.isInvalid() && !tfSector.isInvalid() && !tfZipCode.isInvalid() && !tfAddress.isInvalid() && !tfUserName.isInvalid() && !tfPassword.isInvalid() && !efMail.isInvalid();
    }

    @Override
    public StudentDto collectData(StudentDto model) {

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
        model.setPersonDto(person);

        user.setAdmin(false);
        user.setLanguage(Language.SPANISH);
        return model;
    }

    @Override
    public void security() {

    }


    /**
     * This model binds properties between StudentFormDesign and student-form-design.html
     */
    public interface StudentFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
