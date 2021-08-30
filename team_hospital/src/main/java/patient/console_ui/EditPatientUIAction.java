package patient.console_ui;

import patient.services.EditPatientService;
import patient.services.PatientExistsService;

import java.util.Scanner;

public class EditPatientUIAction implements PatientUIActions {
    private final EditPatientService editPatient;
    private final PatientExistsService patientExists;

    public EditPatientUIAction(EditPatientService editPatient, PatientExistsService patientExists) {
        this.editPatient = editPatient;
        this.patientExists = patientExists;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String changes = "";
        System.out.print("Please enter patient's ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (patientExists.execute(id)) {
            System.out.println("What information would you like to edit? ");
            System.out.println("1. Name");
            System.out.println("2. Surname");
            System.out.println("3. Discharge the patient today");
            int userInput = Integer.parseInt(scanner.nextLine());
            EditAction(scanner, changes, id, userInput);
        } else {
            System.out.println("Patient with that ID doesn't found!");
        }
    }

    private void EditAction(Scanner scanner, String changes, int id, int userInput) {
        if (userInput == 1 || userInput == 2) {
            System.out.println("Enter info for change: ");
            changes = scanner.nextLine();
        }
        editPatient.execute(id, userInput, changes);
        System.out.println("Changes done!");
    }
}