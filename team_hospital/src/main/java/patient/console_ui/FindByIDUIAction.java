package patient.console_ui;

import patient.services.FindPatientByIdService;
import patient.services.PatientExistsService;

import java.util.Scanner;

public class FindByIDUIAction implements PatientUIActions {
    FindPatientByIdService findById;
    PatientExistsService patientExists;

    public FindByIDUIAction(FindPatientByIdService findById, PatientExistsService patientExists) {
        this.findById = findById;
        this.patientExists = patientExists;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter patient`s ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (patientExists.execute(id)) {
            findById.execute(id);
        } else {
            System.out.println("Patient not found!");
        }

    }
}
