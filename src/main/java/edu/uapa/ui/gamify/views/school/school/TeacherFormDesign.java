package edu.uapa.ui.gamify.views.school.school;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.location.AddressDto;
import edu.utesa.lib.models.dtos.location.CountryDto;
import edu.utesa.lib.models.dtos.person.PersonDto;
import edu.utesa.lib.models.dtos.school.GradeDto;
import edu.utesa.lib.models.dtos.school.SchoolDto;
import edu.utesa.lib.models.dtos.school.TeacherDto;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.models.enums.Language;
import edu.utesa.lib.models.enums.person.Gender;
import edu.utesa.lib.models.enums.person.MaritalStatus;
import edu.utesa.lib.models.enums.person.Nationality;
import edu.utesa.lib.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A Designer generated component for the teacher-form-design.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("teacher-form-design")
@HtmlImport("src/views/school/teacher-form-design.html")
public class TeacherFormDesign extends PolymerTemplate<TeacherFormDesign.TeacherFormDesignModel> implements FormStructure<TeacherDto> {

    //    @Id("tfDni")
//    private TextField tfDni;
    @Id("tfFirstName")
    private TextField tfFirstName;
    @Id("tfLastName")
    private TextField tfLastName;
    @Id("dpBirthday")
    private DatePicker dpBirthday;
    @Id("cbGender")
    private ComboBox<Gender> cbGender;
    @Id("cbNationality")
    private ComboBox<Nationality> cbNationality;
    @Id("cbMaritalStatus")
    private ComboBox<MaritalStatus> cbMaritalStatus;
    @Id("cbCountry")
    private ComboBox<CountryDto> cbCountry;
    @Id("tfCity")
    private TextField tfCity;
    @Id("tfSector")
    private TextField tfSector;
    @Id("tfAddress")
    private TextField tfAddress;
    @Id("tfZipCode")
    private TextField tfZipCode;
    @Id("cbSchool")
    private ComboBox<SchoolDto> cbSchool;
    @Id("cbGrade")
    private ComboBox<GradeDto> cbGrade;
    @Id("tfUsername")
    private TextField tfUsername;
    @Id("tfPassword")
    private PasswordField tfPassword;
    @Id("efEmail")
    private EmailField efEmail;

    private AddressDto address;
    private PersonDto person;
    private UserDto user;
    private GradeDto grade;
    private SchoolDto school;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates a new TeacherFormDesign.
     */
    public TeacherFormDesign() {
        // You can initialise any data required for the connected UI components here.
//        tfDni.setLabel(Captions.DNI);
        tfFirstName.setLabel(Captions.FIRST_NAME);
        tfLastName.setLabel(Captions.LAST_NAME);
        dpBirthday.setLabel(Captions.BIRTHDAY);
        cbGender.setLabel(Captions.GENDER);
        cbNationality.setLabel(Captions.NATIONALITY);
        cbMaritalStatus.setLabel(Captions.MARITAL_STATUS);
        cbCountry.setLabel(Captions.COUNTRY);
        tfCity.setLabel(Captions.CITY);
        tfSector.setLabel(Captions.SECTOR);
        tfZipCode.setLabel(Captions.ZIP_CODE);
        tfAddress.setLabel(Captions.ADDRESS);
        cbSchool.setLabel(Captions.SCHOOL);
        cbGrade.setLabel(Captions.GRADE);
        tfUsername.setLabel(Captions.USER_NAME);
        tfPassword.setLabel(Captions.PASSWORD);
        efEmail.setLabel(Captions.EMAIL);

        person = new PersonDto();
        address = new AddressDto();
        school = new SchoolDto();
        grade = new GradeDto();
        user = new UserDto();

        cbGender.setItems(Gender.values());
        cbGender.setItemLabelGenerator(Gender::name);
        cbGender.setValue(Gender.FEMALE);

        cbNationality.setItems(Nationality.values());
        cbNationality.setItemLabelGenerator(Nationality::name);
        cbNationality.setValue(Nationality.DOMINICAN);

        cbMaritalStatus.setItems(MaritalStatus.values());
        cbMaritalStatus.setItemLabelGenerator(MaritalStatus::name);
        cbMaritalStatus.setValue(MaritalStatus.SINGLE);

        dpBirthday.setValue(DateUtils.asLocalDate(new Date("1/1/1996")));
    }

    public void fillCountry(List<CountryDto> items) {
        cbCountry.setItemLabelGenerator(CountryDto::getName);
        cbCountry.setItems(items);
        cbCountry.setValue(items.get(0));
    }

    public void fillSchool(List<SchoolDto> items) {
        cbSchool.setItemLabelGenerator(SchoolDto::getName);
        cbSchool.setItems(items);
        cbSchool.setValue(items.get(0));
    }

    public void fillGrade(List<GradeDto> items) {
        cbGrade.setItemLabelGenerator(GradeDto::getName);
        cbGrade.setItems(items);
        cbGrade.setValue(items.get(0));
    }

    @Override
    public void restore(TeacherDto data) {
        person = data.getPersonDto();
        address = data.getAddressDto();
        school = data.getSchoolDto();
        grade = data.getGradeDto();
        user = data.getUserDto();

//        tfDni.setValue(person.getDni());
        tfFirstName.setValue(person.getFirstNames());
        tfLastName.setValue(person.getLastNames());
        try {
            dpBirthday.setValue(DateUtils.asLocalDate(new SimpleDateFormat("yyyy/MM/dd").parse(person.getBirthday())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cbGender.setValue(person.getGender());
        cbNationality.setValue(person.getNationality());
        cbMaritalStatus.setValue(person.getMaritalStatus());
        cbCountry.setValue(address.getCountryDto());
        tfCity.setValue(address.getCity());
        tfSector.setValue(address.getSector());
        tfZipCode.setValue(address.getZipCode());
        tfAddress.setValue(address.getAddress() != null ? address.getAddress() : "");
        cbSchool.setValue(school);
        cbGrade.setValue(grade);
        tfUsername.setValue(user.getNickName());
        tfPassword.setValue(user.getPassword());
        efEmail.setValue(user.getMail());
    }

    @Override
    public void visualize() {
//        tfDni.setReadOnly(true);
        tfFirstName.setReadOnly(true);
        tfLastName.setReadOnly(true);
        dpBirthday.setReadOnly(true);
        cbGender.setReadOnly(true);
        cbNationality.setReadOnly(true);
        cbMaritalStatus.setReadOnly(true);
        cbCountry.setReadOnly(true);
        tfCity.setReadOnly(true);
        tfSector.setReadOnly(true);
        tfAddress.setReadOnly(true);
        tfZipCode.setReadOnly(true);
        cbSchool.setReadOnly(true);
        cbGrade.setReadOnly(true);
        tfUsername.setReadOnly(true);
        tfPassword.setReadOnly(true);
        efEmail.setReadOnly(true);
    }

    @Override
    public boolean validField() {
//        if (tfDni.isInvalid())
//            return false;
        if (tfFirstName.isInvalid())
            return false;
        if (tfLastName.isInvalid())
            return false;
        if (cbGender.isInvalid())
            return false;
        if (cbNationality.isInvalid())
            return false;
        if (cbMaritalStatus.isInvalid())
            return false;
        if (cbCountry.isInvalid())
            return false;
        if (tfCity.isInvalid())
            return false;
        if (tfSector.isInvalid())
            return false;
        if (tfAddress.isInvalid())
            return false;
        if (tfZipCode.isInvalid())
            return false;
        if (cbSchool.isInvalid())
            return false;
        if (cbGrade.isInvalid())
            return false;
        if (tfUsername.isInvalid())
            return false;
        if (tfPassword.isInvalid())
            return false;
        if (efEmail.isInvalid())
            return false;
        return true;
    }

    @Override
    public TeacherDto collectData(TeacherDto model) {
        address.setName(tfFirstName.getValue());
        address.setCountryDto(cbCountry.getValue());
        address.setCity(tfCity.getValue());
        address.setSector(tfSector.getValue());
        address.setZipCode(tfZipCode.getValue());
        address.setAddress(tfAddress.getValue());
        model.setAddressDto(address);
//        person.setDni(tfDni.getValue());
        person.setFirstNames(tfFirstName.getValue());
        person.setLastNames(tfLastName.getValue());
        person.setBirthday(dpBirthday.getValue().toString());
        person.setGender(cbGender.getValue());
        person.setNationality(cbNationality.getValue());
        person.setMaritalStatus(cbMaritalStatus.getValue());
        person.setAddressDto(address);
        model.setPersonDto(person);

        user.setNickName(tfUsername.getValue());
        user.setPassword(tfPassword.getValue());
        user.setAdmin(false);
        user.setLanguage(Language.SPANISH);
        user.setMail(efEmail.getValue());
        model.setUserDto(user);

        model.setSchoolDto(cbSchool.getValue());
        model.setGradeDto(cbGrade.getValue());

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
