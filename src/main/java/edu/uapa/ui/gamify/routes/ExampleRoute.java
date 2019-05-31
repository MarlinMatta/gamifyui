package edu.uapa.ui.gamify.routes;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import edu.uapa.ui.gamify.utils.Constants;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Route(Constants.EXAMPLE_ROUTE)
public class ExampleRoute extends VerticalLayout {

    public ExampleRoute() {
        Checkbox indeterminateCheckbox = new Checkbox("Disabled Checkbox");
        indeterminateCheckbox.setIndeterminate(true);
        add(indeterminateCheckbox);


        ComboBox<String> comboBox = new ComboBox<>("Artists");
        List<String> numberList = Arrays.asList("Uno", "Dos", "Tres", "Cuatros", "Cincos");
        comboBox.setItems(numberList);
        comboBox.addValueChangeListener(event -> Notification.show(event.toString()));
        add(comboBox);

        DatePicker datePicker = new DatePicker();
        datePicker.setLabel("Select a day within this month");
        datePicker.setPlaceholder("Date within this month");
        LocalDate now = LocalDate.now();
        datePicker.setMin(now.withDayOfMonth(1));
        datePicker.setMax(now.withDayOfMonth(now.lengthOfMonth()));
        datePicker.addValueChangeListener(
                event -> Notification.show(event.getValue().toString()));
        add(datePicker);

        TextField textField = new TextField();
        textField.setLabel("Text label");
        textField.setValue("Text value");
        textField.setAutoselect(true);
        add(textField);

        NumberField dollarField = new NumberField("Dollars");
        dollarField.setPrefixComponent(new Span("$"));
        add(dollarField);

        NumberField euroField = new NumberField("Euros");
        euroField.setSuffixComponent(new Span("€"));
        add(euroField);

        NumberField stepperField = new NumberField("Stepper");
        stepperField.setValue(1d);
        stepperField.setMin(0);
        stepperField.setMax(10);
        stepperField.setHasControls(true);
        add(stepperField);

        EmailField emailField = new EmailField("Email");
        emailField.addValueChangeListener(event -> Notification.show(
                String.format("Email field value changed from '%s' to '%s'",
                        event.getOldValue(), event.getValue())));
        add(emailField);

        TextField textFieldLumoTheme = new TextField();
        textFieldLumoTheme.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textFieldLumoTheme.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        add(textFieldLumoTheme);

        ListBox<String> listBox = new ListBox<>();
        listBox.setItems("Bread", "Butter", "Milk");
        listBox.addValueChangeListener(event -> Notification.show(String.format(
                "Selection changed from %s to %s, selection is from client: %s",
                event.getOldValue(), event.getValue(), event.isFromClient())));
        add(listBox);
        NativeButton button = new NativeButton("Select Milk",
                event -> listBox.setValue("Milk"));
        add(button);

        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password field label");
        passwordField.setPlaceholder("placeholder text");
        passwordField.addValueChangeListener(event -> Notification.show(
                String.format("Password field value changed from '%s' to '%s'",
                        event.getOldValue(), event.getValue())));
        add(passwordField);
        NativeButton button1 = new NativeButton("Toggle eye icon", event -> passwordField.setRevealButtonVisible(
                !passwordField.isRevealButtonVisible()));
        add(button1);

        RadioButtonGroup<String> group = new RadioButtonGroup<>();
        group.setItems("foo", "bar", "baz");
        group.setLabel("Group label");
        group.setErrorMessage("Field has been set to invalid from server side");
        add(group);
        NativeButton button2 = new NativeButton("Switch validity state",
                event -> group.setInvalid(!group.isInvalid()));
        add(button2);

        List<Locale> locales = Arrays.asList(new Locale("Other"),
                new Locale("es"),
                new Locale("fi"),
                new Locale("en"));

        Select<Locale> select = new Select<>();
        select.setPlaceholder("Language");
        select.setTextRenderer(Locale::getDisplayLanguage);
        select.setItemLabelGenerator(locale -> {
            if ("Other".equalsIgnoreCase(locale.getLanguage())) {
                return "";
            } else {
                return locale.getISO3Language();
            }
        });
        select.setItems(locales);
        add(select);

        TimePicker timePicker = new TimePicker();
        timePicker.addValueChangeListener(
                event -> Notification.show(event.getValue().toString()));
        add(timePicker);

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.addSucceededListener(event -> Notification.show(event.getFileName()));
        add(upload);
    }
}
