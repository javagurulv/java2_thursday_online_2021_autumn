package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.DeletePatientService;

@DIComponent
public class DeletePatientUIAction implements PatientUIActions {

    @DIDependency private DeletePatientService deletePatient;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        DeletePatientRequest request = new DeletePatientRequest(getUserInput.getUserLongInput("Please, enter patient ID: "));
        DeletePatientResponse response = deletePatient.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription())
            );
        } else {
            if (response.isPatientDeleted()) {
                System.out.println("Patient with ID " + request.getIdRequest() + " was successfully deleted.");
            } else {
                System.out.println("User with ID:" + response.getIdResponse() + " not found!");
            }

        }
    }
}
