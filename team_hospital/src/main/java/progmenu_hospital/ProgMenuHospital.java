package progmenu_hospital;

import doctor.console_ui.*;
import database.DoctorDatabaseImpl;
import doctor.services.*;
import patient.InputNumChecker;
import patient.console_ui.*;
import database.PatientDatabaseImpl;
import patient.services.*;

public class ProgMenuHospital {

    private static final PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    private static final DoctorDatabaseImpl doctorDatabase = new DoctorDatabaseImpl();
    private static final InputNumChecker inputNumChecker = new InputNumChecker();

    private static final patient.console_ui.PatientUIActions[] PatientUIActions = {
            new AddPatientUIAction(new AddPatientService(patientDatabase)),
            new ShowAllPatientsUIAction(new ShowAllPatientsService(patientDatabase)),
            new FindPatientByIDUIAction(new FindPatientByIdService(patientDatabase), new PatientExistsService(patientDatabase)),
            new DeletePatientUIAction(new DeletePatientService(patientDatabase), new PatientExistsService(patientDatabase)),
            new EditPatientUIAction(new EditPatientService(patientDatabase), new PatientExistsService(patientDatabase))};

    private static final DoctorUIActions[] doctorUIActions = {
            new AddDoctorUIAction(new AddDoctorService(doctorDatabase)),
            new ShowAllDoctorsUIAction(new ShowAllDoctorsService(doctorDatabase)),
            new FindDoctorByIDUIAction(new FindDoctorByIdService(doctorDatabase), new DoctorExistsService(doctorDatabase)),
            new DeleteDoctorUIAction(new DeleteDoctorService(doctorDatabase), new DoctorExistsService(doctorDatabase)),
            new EditDoctorUIAction(new EditDoctorService(doctorDatabase), new DoctorExistsService(doctorDatabase))};


    //Menu
    public static void action() {
        while (true) {
            programMenu();
            int userInput = getUserInput();

            switch (userInput) {
                case 1 -> patientMenu();
                case 2 -> doctorMenu();
                case 3 -> System.exit(0);
            }
            System.out.println();
        }
    }

    private static int getUserInput() {
        System.out.println("Enter menu item number to execute: ");
        return inputNumChecker.execute(1, 3);
    }

    private static void programMenu() {
        System.out.println("Program menu: ");
        System.out.println("Press 1 to see patient actions.");
        System.out.println("Press 2 to see doctor actions.");
        System.out.println("Press 3 for Exit.");

        System.out.println();
    }

    private static void patientMenu() {
        int userInput;
        do {
            userInput = menuPatient();
        } while (userInput != 6);
    }

    private static int menuPatient() {
        int userInput;
        System.out.println("1. Add a new patient");
        System.out.println("2. Show all patients");
        System.out.println("3. Find the patient by ID");
        System.out.println("4. Delete the patient by ID");
        System.out.println("5. Edit the patient's information");
        System.out.println("6. Exit");
        userInput = inputNumChecker.execute(1, 6);
        patientUserActions(userInput);
        System.out.println();
        return userInput;
    }

    private static void doctorMenu() {
        int userInput;
        do {
            userInput = menuDoctor();
        } while (userInput != 6);
    }

    private static int menuDoctor() {
        int userInput;
        System.out.println("1. Add a new doctor");
        System.out.println("2. Show all doctors");
        System.out.println("3. Find the doctor by ID");
        System.out.println("4. Delete the doctor by ID");
        System.out.println("5. Edit the doctor's information");
        System.out.println("6. Exit");
        userInput = inputNumChecker.execute(1, 6);
        doctorUserActions(userInput);
        System.out.println();
        return userInput;
    }

    private static void patientUserActions(int num) {
        switch (num) {
            case 1 -> PatientUIActions[0].execute();
            case 2 -> PatientUIActions[1].execute();
            case 3 -> PatientUIActions[2].execute();
            case 4 -> PatientUIActions[3].execute();
            case 5 -> PatientUIActions[4].execute();
        }
    }

    private static void doctorUserActions(int num) {
        switch (num) {
            case 1 -> doctorUIActions[0].execute();
            case 2 -> doctorUIActions[1].execute();
            case 3 -> doctorUIActions[2].execute();
            case 4 -> doctorUIActions[3].execute();
            case 5 -> doctorUIActions[4].execute();
        }
    }
}