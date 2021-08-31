package patient.console_ui;

import patient.services.DeletePatientService;
import patient.services.PatientExistsService;

import java.util.Scanner;

public class DeletePatientUIAction implements PatientUIActions {
    private final DeletePatientService deletePatient;
    private final PatientExistsService patientExists;

    public DeletePatientUIAction(DeletePatientService deletePatient, PatientExistsService patientExists) {
        this.deletePatient = deletePatient;
        this.patientExists = patientExists;
    }

    public void execute() {
        System.out.println("Please, enter patient's ID: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        if (patientExists.execute(id)) {
            deletePatient.execute(id);
            System.out.println("Patient with ID = " + id + " was successfully deleted.");
        } else {
            System.out.println("Patient doesn't exist!");
        }
    }
}
