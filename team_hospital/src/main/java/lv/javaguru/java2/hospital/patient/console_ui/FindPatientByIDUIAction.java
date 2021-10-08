package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.services.FindPatientByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindPatientByIDUIAction implements PatientUIActions {

   @Autowired private FindPatientByIdService findByIDService;

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
