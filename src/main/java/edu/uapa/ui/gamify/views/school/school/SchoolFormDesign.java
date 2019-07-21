package edu.uapa.ui.gamify.views.school.school;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import edu.uapa.ui.gamify.models.interfaces.FormStructure;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.utesa.lib.models.dtos.location.AddressDto;
import edu.utesa.lib.models.dtos.school.SchoolDto;

/**
 * A Designer generated component for the school-form-desing.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("school-form-desing")
@HtmlImport("src/views/school/school-form-desing.html")
public class SchoolFormDesign extends PolymerTemplate<SchoolFormDesign.SchoolFormDesingModel> implements FormStructure<SchoolDto> {

    @Id("tfName")
    private TextField tfName;
    @Id("tfDistrict")
    private TextField tfDistrict;
    @Id("cbCity")
    private ComboBox<String> cbCity;
    @Id("tfSector")
    private TextField tfSector;
    @Id("tfAddress")
    private TextField tfAddress;
    @Id("tfZipCode")
    private TextField tfZipCode;

    private AddressDto address;

    /**
     * Creates a new SchoolFormDesign.
     */
    public SchoolFormDesign() {
        // You can initialise any data required for the connected UI components here.
        tfName.setLabel(Captions.NAME);
        tfDistrict.setLabel(Captions.DISTRICT);
        cbCity.setLabel(Captions.CITY);
        tfSector.setLabel(Captions.SECTOR);
        tfZipCode.setLabel(Captions.ZIP_CODE);
        tfAddress.setLabel(Captions.ADDRESS);

        address = new AddressDto();
    }

    @Override
    public void restore(SchoolDto data) {
        address = data.getAddressDto();
        tfName.setValue(data.getName());
        tfDistrict.setValue(data.getDistrict());
        cbCity.setValue(data.getAddressDto().getCity());
        tfSector.setValue(data.getAddressDto().getSector());
        tfZipCode.setValue(data.getAddressDto().getZipCode());
        tfAddress.setValue(data.getAddressDto().getAddress());
    }

    @Override
    public void visualize() {
        tfName.setReadOnly(true);
        tfDistrict.setReadOnly(true);
        cbCity.setReadOnly(true);
        tfSector.setReadOnly(true);
        tfZipCode.setReadOnly(true);
        tfAddress.setReadOnly(true);

    }

    @Override
    public boolean validField() {
        return !tfName.isInvalid() && !tfDistrict.isInvalid() && !tfAddress.isInvalid();
    }

    @Override
    public SchoolDto collectData(SchoolDto model) {
        model.setName(tfName.getValue());
        model.setDistrict(tfDistrict.getValue());
        address.setName(tfName.getValue());
        address.setCity(cbCity.getValue());
        address.setSector(tfSector.getValue());
        address.setZipCode(tfZipCode.getValue());
        model.setAddressDto(address);
        return model;
    }


    @Override
    public void security() {

    }

    /**
     * This model binds properties between SchoolFormDesign and school-form-desing.html
     */
    public interface SchoolFormDesingModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
