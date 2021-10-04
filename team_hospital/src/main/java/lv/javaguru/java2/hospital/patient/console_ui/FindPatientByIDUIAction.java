package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.services.FindPatientByIdService;
import java.util.Scanner;

@DIComponent
public class FindPatientByIDUIAction implements PatientUIActions {

   @DIDependency private FindPatientByIdService findByIDService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter patient ID: ");
        FindPatientByIdRequest request = new FindPatientByIdRequest(scanner.nextLong());
        FindPatientByIDResponse response = findByIDService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription())
            );
        } else {
                System.out.println(response.getPatient());
        }
    }
}
