package lv.javaguru.java2.hospital.visits.console_ui;

import lv.javaguru.java2.hospital.visits.request.PatientVisitRequest;
import lv.javaguru.java2.hospital.visits.responses.PatientVisitResponse;
import lv.javaguru.java2.hospital.visits.services.PatientsVisitService;

import java.util.Scanner;

public class PatientVisitUIAction {
    private final PatientsVisitService patientsVisitService;

    public PatientVisitUIAction(PatientsVisitService patientsVisitService) {
        this.patientsVisitService = patientsVisitService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter patients personal code: ");
        String patientsPersonalCode = scanner.nextLine();
        System.out.println("Please enter doctor`s name: ");
        String doctorName = scanner.nextLine();
        System.out.println("Please enter doctor`s surname: ");
        String doctorSurname = scanner.nextLine();
        System.out.println("Please enter visit date: ");
        String visitDate = scanner.nextLine();

        PatientVisitRequest request =
                new PatientVisitRequest(patientsPersonalCode, doctorName,
                        doctorSurname, visitDate);
        PatientVisitResponse response = patientsVisitService.recordPatientVisit(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError
                    -> System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription()));
        } else {
            System.out.println(response.getPatientVisit());
        }
    }
}
