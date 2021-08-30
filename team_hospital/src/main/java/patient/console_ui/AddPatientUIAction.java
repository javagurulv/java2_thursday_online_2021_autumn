package patient.console_ui;

import patient.services.AddPatientService;
import java.util.Scanner;

public class AddPatientUIAction implements PatientUIActions {
   private final AddPatientService addPatient;

    public AddPatientUIAction(AddPatientService addPatient) {
        this.addPatient = addPatient;
    }

    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter patient's name, surname and personal code: ");
        String[] patientInfo = scanner.nextLine().split(" ");
        addPatient.execute(patientInfo[0], patientInfo[1], patientInfo[2]);
        System.out.println(patientInfo[0] + " " + patientInfo[1] + " was successfully added.");
    }
}