package lv.javaguru.java2.hospital.progmenu_hospital;

import lv.javaguru.java2.hospital.PatientApplicationContext;
import lv.javaguru.java2.hospital.InputNumChecker;
import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.console_ui.*;
import lv.javaguru.java2.hospital.doctor.core.services.*;
import lv.javaguru.java2.hospital.doctor.core.services.validators.*;
import lv.javaguru.java2.hospital.patient.console_ui.*;
import lv.javaguru.java2.hospital.visits.console_ui.PatientVisitUIAction;
import lv.javaguru.java2.hospital.visits.services.PatientsVisitService;
import lv.javaguru.java2.hospital.visits.services.validators.PatientVisitValidator;

public class ProgMenuHospital {

    private static final PatientApplicationContext patientApplicationContext = new PatientApplicationContext();
    private static final DoctorDatabaseImpl doctorDatabase = new DoctorDatabaseImpl();
    private static final InputNumChecker inputNumChecker = new InputNumChecker();
    private static final PatientVisitUIAction patientVisit =
            new PatientVisitUIAction(new PatientsVisitService
                    (patientApplicationContext.getBean(PatientDatabase.class), doctorDatabase,
                            new VisitDatabaseImpl(), new PatientVisitValidator()));

    private static final DoctorUIActions[] doctorUIActions = {
            new AddDoctorUIAction(new AddDoctorService(doctorDatabase, new AddDoctorRequestValidator())),
            new ShowAllDoctorsUIAction(new ShowAllDoctorsService(doctorDatabase)),
            new DeleteDoctorUIAction(new DeleteDoctorService(doctorDatabase, new DeleteDoctorRequestValidator()), new DoctorExistsService(doctorDatabase)),
            new EditDoctorUIAction(new EditDoctorService(doctorDatabase, new EditDoctorRequestValidator()), new DoctorExistsService(doctorDatabase)),
            new SearchDoctorsUIAction(new SearchDoctorsService(doctorDatabase,
                    new SearchDoctorsRequestValidator(new SearchDoctorsRequestFieldValidator(), new OrderingValidator(), new PagingValidator())))};


    //Menu
    public static void action() {
        while (true) {
            programMenu();
            int userInput = getUserInput();

            switch (userInput) {
                case 1 -> patientMenu();
                case 2 -> doctorMenu();
                case 3 -> patientVisit.execute();
                case 4 -> System.exit(0);
            }
            System.out.println();
        }
    }

    private static int getUserInput() {
        System.out.println("Enter menu item number to execute: ");
        return inputNumChecker.execute(1, 4);
    }

    private static void programMenu() {
        System.out.println("Program menu: ");
        System.out.println("Press 1 to see patient actions.");
        System.out.println("Press 2 to see doctor actions.");
        System.out.println("Press 3 to make a visit.");
        System.out.println("Press 4 for Exit.");

        System.out.println();
    }

    private static void patientMenu() {
        int userInput;
        do {
            userInput = menuPatient();
        } while (userInput != 7);
    }

    private static int menuPatient() {
        int userInput;
        System.out.println("1. Add a new patient");
        System.out.println("2. Show all patients");
        System.out.println("3. Find patient by ID");
        System.out.println("4. Delete the by ID");
        System.out.println("5. Edit the patient's information");
        System.out.println("6. Patient`s search");
        System.out.println("7. Exit");
        userInput = inputNumChecker.execute(1, 7);
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
        System.out.println("3. Delete the doctor by ID");
        System.out.println("4. Edit the doctor's information");
        System.out.println("5. Search doctors");
        System.out.println("6. Exit");
        userInput = inputNumChecker.execute(1, 7);
        doctorUserActions(userInput);
        System.out.println();
        return userInput;
    }

    private static void patientUserActions(int num) {
        switch (num) {
            case 1 -> {
                AddPatientUIAction addPatientUIAction = patientApplicationContext.getBean(AddPatientUIAction.class);
                addPatientUIAction.execute();
            }
            case 2 -> {
                ShowAllPatientsUIAction showAllPatientsUIAction = patientApplicationContext.getBean(ShowAllPatientsUIAction.class);
                showAllPatientsUIAction.execute();
            }
            case 3 -> {
                FindPatientByIDUIAction findPatientByIDUIAction = patientApplicationContext.getBean(FindPatientByIDUIAction.class);
                findPatientByIDUIAction.execute();
            }
            case 4 -> {
                DeletePatientUIAction deletePatientUIAction = patientApplicationContext.getBean(DeletePatientUIAction.class);
                deletePatientUIAction.execute();
            }
            case 5 -> {
                EditPatientUIAction editPatientUIAction = patientApplicationContext.getBean(EditPatientUIAction.class);
                editPatientUIAction.execute();
            }
            case 6 -> {
                SearchPatientsUIAction searchPatientsUIAction = patientApplicationContext.getBean(SearchPatientsUIAction.class);
                searchPatientsUIAction.execute();
            }
        }
    }

    private static void doctorUserActions(int num) {
        switch (num) {
            case 1 -> doctorUIActions[0].execute();
            case 2 -> doctorUIActions[1].execute();
            case 3 -> doctorUIActions[2].execute();
            case 4 -> doctorUIActions[3].execute();
            case 5 -> doctorUIActions[4].execute();
            case 6 -> programMenu();
        }
    }
}