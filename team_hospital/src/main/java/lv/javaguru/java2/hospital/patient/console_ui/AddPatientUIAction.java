package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;

import java.util.Scanner;

public class AddPatientUIAction implements PatientUIActions {
    private final AddPatientService addPatient;

    public AddPatientUIAction(AddPatientService addPatient) {
        this.addPatient = addPatient;
    }

    public void execute() {
        String[] patientInfo = getUserInput();
        AddPatientRequest request = new AddPatientRequest(patientInfo[0], patientInfo[1], patientInfo[2]);
        AddPatientResponse response = addPatient.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription())
            );
        } else {
            System.out.println(patientInfo[0] + " " + patientInfo[1] + " was successfully added.");
        }
    }

   private String[] getUserInput(){
       Scanner scanner = new Scanner(System.in);
       System.out.println("Please enter patient name, surname and personal code: ");
       return scanner.nextLine().split(" ");
   }
}
