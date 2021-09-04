package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.services.FindPatientByIdService;
import lv.javaguru.java2.hospital.patient.services.PatientExistsService;

import java.util.Scanner;

public class FindPatientByIDUIAction implements PatientUIActions {
    private final FindPatientByIdService findById;
    private final PatientExistsService patientExists;

    public FindPatientByIDUIAction(FindPatientByIdService findById, PatientExistsService patientExists) {
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
