package edu.uapa.ui.gamify.views.school.school;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.location.AddressDto;
import edu.utesa.lib.models.dtos.location.CountryDto;
import edu.utesa.lib.models.dtos.school.SchoolDto;

import java.util.List;

/**
 * A Designer generated component for the school-form-design template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("school-form-design")
@HtmlImport("src/views/school/school-form-design.html")
public class SchoolFormDesign extends PolymerTemplate<SchoolFormDesign.SchoolFormDesignModel> implements FormStructure<SchoolDto> {

    @Id("tfName")
    private TextField tfName;
    @Id("tfDistrict")
    private TextField tfDistrict;
    @Id("cbCountry")
    private ComboBox<CountryDto> cbCountry;
    @Id("tfCity")
    private TextField tfCity;
    @Id("tfSector")
    private TextField tfSector;
    @Id("tfZipCode")
    private TextField tfZipCode;
    @Id("tfAddress")
    private TextField tfAddress;

    private AddressDto address;

    /**
     * Creates a new SchoolFormDesign.
     */
    public SchoolFormDesign() {
        // You can initialise any data required for the connected UI components here.
        tfName.setLabel(Captions.NAME);
        tfDistrict.setLabel(Captions.DISTRICT);
        tfCity.setLabel(Captions.CITY);
        cbCountry.setLabel(Captions.COUNTRY);
        tfSector.setLabel(Captions.SECTOR);
        tfZipCode.setLabel(Captions.ZIP_CODE);
        tfAddress.setLabel(Captions.ADDRESS);

        address = new AddressDto();
    }

    public void fillCountry(List<CountryDto> items) {
        cbCountry.setItemLabelGenerator(CountryDto::getName);
        cbCountry.setItems(items);
        cbCountry.setValue(items.get(0));
    }

    @Override
    public void restore(SchoolDto data) {
        address = data.getAddressDto();

        tfName.setValue(data.getName());
        tfDistrict.setValue(data.getDistrict());
        tfCity.setValue(data.getAddressDto().getCity());
        cbCountry.setValue(data.getAddressDto().getCountryDto());
        tfSector.setValue(data.getAddressDto().getSector());
        tfZipCode.setValue(data.getAddressDto().getZipCode());
        tfAddress.setValue(data.getAddressDto().getAddress());
    }

    @Override
    public void visualize() {
        tfName.setReadOnly(true);
        tfDistrict.setReadOnly(true);
        tfCity.setReadOnly(true);
        cbCountry.setReadOnly(true);
        tfSector.setReadOnly(true);
        tfZipCode.setReadOnly(true);
        tfAddress.setReadOnly(true);
    }

    @Override
    public boolean validField() {
        if (tfName.isInvalid())
            return false;
        if (tfDistrict.isInvalid())
            return false;
        if (cbCountry.isInvalid())
            return false;
        if (tfCity.isInvalid())
            return false;
        if (tfSector.isInvalid())
            return false;
        if (tfZipCode.isInvalid())
            return false;
        if (tfAddress.isInvalid())
            return false;
        return true;
    }

    @Override
    public SchoolDto collectData(SchoolDto model) {
        address.setName(tfName.getValue());
        address.setCity(tfCity.getValue());
        address.setCountryDto(cbCountry.getValue());
        address.setSector(tfSector.getValue());
        address.setZipCode(tfZipCode.getValue());
        address.setAddress(tfAddress.getValue());

        model.setName(tfName.getValue());
        model.setDistrict(tfDistrict.getValue());
        model.setAddressDto(address);
        return model;
    }

    @Override
    public void security() {

    }

    /**
     * This model binds properties between SchoolFormDesign and school-form-design
     */
    public interface SchoolFormDesignModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
