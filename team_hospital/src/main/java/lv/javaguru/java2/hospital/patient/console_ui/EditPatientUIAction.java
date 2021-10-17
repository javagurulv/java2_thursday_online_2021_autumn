package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.PatientEnumChecker;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.EditPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
@Component
public class EditPatientUIAction implements PatientUIActions {

    @Autowired
    private EditPatientService editPatient;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        PatientEnumChecker checker = new PatientEnumChecker();
        Long id = getUserInput.getUserLongInput("Please enter patient ID: ");
        String userInputString = getUserInput
                        .getUserStringInput("What information would you like to edit? (NAME||SURNAME||PERSONAL_CODE)?")
                        .toUpperCase(Locale.ROOT);
        EditPatientEnum editEnum = checker.validateEnum(userInputString);
        if(editEnum == null){
            return;
        }
        String changes = getUserInput.getUserStringInput("Please enter information for changes: ");
        EditPatientRequest request = new EditPatientRequest(id, editEnum, changes);
        EditPatientResponse response = editPatient.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription())
            );
        } else {
                System.out.println("Changes are made!");
        }
    }
}