package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.services.FindPatientByIdService;
import java.util.Scanner;

public class FindPatientByIDUIAction implements PatientUIActions {
    private final FindPatientByIdService findByIDService;

    public FindPatientByIDUIAction(FindPatientByIdService findById) {
        this.findByIDService = findById;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter patient`s ID: ");
        FindPatientByIdRequest request = new FindPatientByIdRequest(scanner.nextLine());
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
