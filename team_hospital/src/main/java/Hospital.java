import doctor.Doctor;
import doctor.DoctorActions;
import doctor.console_ui.*;
import doctor.database.DoctorDatabaseImpl;
import doctor.services.*;
import patient.InputNumChecker;
import patient.console_ui.*;
import patient.database.PatientDatabaseImpl;
import patient.services.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospital {
    public static void main(String[] args) {
        Hospital test = new Hospital();
        test.action();
    }

    private final PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    private final DoctorDatabaseImpl doctorDatabase = new DoctorDatabaseImpl();
    private final Scanner scanner = new Scanner(System.in);
    private final DoctorActions doctorActions = new DoctorActions();
    private final InputNumChecker inputNumChecker = new InputNumChecker();

    private final PatientUIActions[] PatientUIActions = {
            new AddPatientUIAction(new AddPatientService(patientDatabase)),
            new ShowAllPatientsUIAction(new ShowAllPatientsService(patientDatabase)),
            new FindByIDUIAction(new FindPatientByIdService(patientDatabase), new PatientExistsService(patientDatabase)),
            new DeletePatientUIAction(new DeletePatientService(patientDatabase), new PatientExistsService(patientDatabase)),
            new EditPatientUIAction(new EditPatientService(patientDatabase), new PatientExistsService(patientDatabase))};

    private final PatientActions patientActions = new PatientActions();

    private final DoctorUIActions[] doctorUIActions = {
            new AddDoctorUIAction(new AddDoctorService(doctorDatabase)),
            new ShowAllDoctorsUIAction(new ShowAllDoctorsService(doctorDatabase)),
            new FindByIDUIAction(new FindDoctorByIdService(doctorDatabase), new DoctorExistsService(doctorDatabase)),
            new DeleteDoctorUIAction(new DeleteDoctorService(doctorDatabase), new DoctorExistsService(doctorDatabase)),
            new EditDoctorUIAction(new EditDoctorService(doctorDatabase), new DoctorExistsService(doctorDatabase))};

    public void action() {
        while (true) {
            System.out.println("Program menu: ");
            System.out.println("Press 1 to see patient actions.");
            System.out.println("Press 2 to see doctor actions.");
            System.out.println("Press 3 for Exit.");

            System.out.println();

            System.out.println("Enter menu item number to execute: ");
            int userInput = inputNumChecker.execute(1, 3);

            switch (userInput) {
                case 1 -> patientMenu();
                case 2 -> doctorMenu();
                case 3 -> System.exit(0);
            }
            System.out.println();
        }
    }

    private void patientMenu() {
        int userInput;
        do {
            System.out.println("1. Add a new patient");
            System.out.println("2. Show all patients");
            System.out.println("3. Find the patient by ID");
            System.out.println("4. Delete the patient by ID");
            System.out.println("5. Edit the patient's information");
            System.out.println("6. Exit");
            userInput = scanner.nextInt();
            patientUserActions(userInput);
        } while (userInput != 6);
    }

    private void doctorMenu() {
        int userInput;
        do {
            System.out.println("1. Add a new doctor");
            System.out.println("2. Show all doctors");
            System.out.println("3. Find the doctor by ID");
            System.out.println("4. Delete the doctor by ID");
            System.out.println("5. Edit the doctor's information");
            System.out.println("6. Exit");
            userInput = scanner.nextInt();
            doctorUserActions(userInput);
        } while (userInput != 6);
    }

    private void patientUserActions(int num) {
        switch (num) {
            case 1 -> PatientUIActions[0].execute();
            case 2 -> PatientUIActions[1].execute();
            case 3 -> PatientUIActions[2].execute();
            case 4 -> PatientUIActions[3].execute();
            case 5 -> PatientUIActions[4].execute();
        }
    }

    private void doctorUserActions(int num) {
        switch (num) {
            case 1 -> doctorUIActions[0].execute();
            case 2 -> doctorUIActions[1].execute();
            case 3 -> doctorUIActions[2].execute();
            case 4 -> doctorUIActions[3].execute();
            case 5 -> doctorUIActions[4].execute();
        }
    }
}