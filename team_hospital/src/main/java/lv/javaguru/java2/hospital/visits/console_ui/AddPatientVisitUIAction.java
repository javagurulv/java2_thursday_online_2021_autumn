package lv.javaguru.java2.hospital.visits.console_ui;

import lv.javaguru.java2.hospital.visits.core.request.AddPatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.AddPatientVisitResponse;
import lv.javaguru.java2.hospital.visits.core.services.AddPatientsVisitService;

import java.util.Scanner;

public class AddPatientVisitUIAction {
    private final AddPatientsVisitService patientsVisitService;

    public AddPatientVisitUIAction(AddPatientsVisitService patientsVisitService) {
        this.patientsVisitService = patientsVisitService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter patient personal code: ");
        String patientsPersonalCode = scanner.nextLine();
        System.out.println("Please enter doctor name: ");
        String doctorName = scanner.nextLine();
        System.out.println("Please enter doctor surname: ");
        String doctorSurname = scanner.nextLine();
        System.out.println("Please enter visit date in format dd/MM/yyyy HH:mm: ");
        String visitDate = scanner.nextLine();

        AddPatientVisitRequest request =
                new AddPatientVisitRequest(patientsPersonalCode, doctorName,
                        doctorSurname, visitDate);
        AddPatientVisitResponse response = patientsVisitService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError
                    -> System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription()));
        } else {
            System.out.println(
                    "Visit registered for the patient: "
                            + response.getPatientVisit().getPatient().getName() + " "
                            + response.getPatientVisit().getPatient().getSurname()
                            + ", to the doctor: "
                            + response.getPatientVisit().getDoctor().getName() + " "
                            + response.getPatientVisit().getDoctor().getSurname() + ".");
            System.out.println("Visit date: " + response.getPatientVisit().getVisitDate()
                    + ", visit id: " + response.getPatientVisit().getVisitID() + ".");
        }
    }
}
