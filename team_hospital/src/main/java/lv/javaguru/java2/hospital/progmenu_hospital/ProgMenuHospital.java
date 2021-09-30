package lv.javaguru.java2.hospital.progmenu_hospital;

import lv.javaguru.java2.hospital.DoctorApplicationContext;
import lv.javaguru.java2.hospital.InputNumChecker;
import lv.javaguru.java2.hospital.PatientApplicationContext;
import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.console_ui.*;
import lv.javaguru.java2.hospital.patient.console_ui.*;
import lv.javaguru.java2.hospital.visits.console_ui.*;
import lv.javaguru.java2.hospital.visits.core.services.AddPatientsVisitService;
import lv.javaguru.java2.hospital.visits.core.services.DeletePatientVisitService;
import lv.javaguru.java2.hospital.visits.core.services.EditPatientVisitService;
import lv.javaguru.java2.hospital.visits.core.services.ShowAllPatientVisitService;
import lv.javaguru.java2.hospital.visits.core.services.validators.AddPatientVisitValidator;
import lv.javaguru.java2.hospital.visits.core.services.validators.DeletePatientVisitValidator;
import lv.javaguru.java2.hospital.visits.core.services.validators.EditPatientVisitValidator;

public class ProgMenuHospital {

    private static final PatientApplicationContext patientApplicationContext = new PatientApplicationContext();
    private static final DoctorApplicationContext doctorApplicationContext = new DoctorApplicationContext();

    private static final InputNumChecker inputNumChecker = new InputNumChecker();


    private static final AddPatientVisitUIAction addPatientVisit =
            new AddPatientVisitUIAction(new AddPatientsVisitService
                    (patientApplicationContext.getBean(PatientDatabase.class),
                            doctorApplicationContext.getBean(DoctorDatabase.class),
                            new VisitDatabaseImpl(),
                            new AddPatientVisitValidator(patientApplicationContext.getBean(PatientDatabase.class),
                                    doctorApplicationContext.getBean(DoctorDatabase.class))));

    private static final DeletePatientVisitUIAction deletePatientVisitUIAction =
            new DeletePatientVisitUIAction(
                    new DeletePatientVisitService(new VisitDatabaseImpl(), new DeletePatientVisitValidator()));

    private static final VisitDatabaseImpl visitDatabase = new VisitDatabaseImpl();
    private static final ShowAllPatientVisitService showAllPatientVisitService =
            new ShowAllPatientVisitService(visitDatabase);
    private static final ShowAllPatientVisitUIAction showAllPatientVisitUIAction =
            new ShowAllPatientVisitUIAction(showAllPatientVisitService);
    private static final EditPatientVisitValidator editPatientVisitValidator = new EditPatientVisitValidator();
    private static final EditPatientVisitService editPatientVisitService =
            new EditPatientVisitService(visitDatabase, editPatientVisitValidator);
    private static final EditPatientVisitUIAction editPatientVisitUIAction =
            new EditPatientVisitUIAction(editPatientVisitService);
    private static final ExitVisitUIAction exitVisit = new ExitVisitUIAction();

    //Menu
    public static void action() {
        while (true) {
            programMenu();
            int userInput = getUserInput();

            switch (userInput) {
                case 1 -> patientMenu();
                case 2 -> doctorMenu();
                case 3 -> patientVisitMenu();
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
        System.out.println("Press 3 to see patient visit actions.");
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
            case 1 -> {
                AddDoctorUIAction uiAction = doctorApplicationContext.getBean(AddDoctorUIAction.class);
                uiAction.execute();
            }
            case 2 -> {
                ShowAllDoctorsUIAction uiAction = doctorApplicationContext.getBean(ShowAllDoctorsUIAction.class);
                uiAction.execute();
            }
            case 3 -> {
                DeleteDoctorUIAction uiAction = doctorApplicationContext.getBean(DeleteDoctorUIAction.class);
                uiAction.execute();
            }
            case 4 -> {
                EditDoctorUIAction uiAction = doctorApplicationContext.getBean(EditDoctorUIAction.class);
                uiAction.execute();
            }
            case 5 -> {
                SearchDoctorsUIAction uiAction = doctorApplicationContext.getBean(SearchDoctorsUIAction.class);
                uiAction.execute();
            }
            case 6 -> {
                ExitDoctorUIAction uiAction = doctorApplicationContext.getBean(ExitDoctorUIAction.class);
                uiAction.execute();
            }
        }
    }

    private static void patientVisitMenu() {
        int userInput;
        do {
            userInput = menuPatientVisit();
        } while (userInput != 3);
    }

    private static int menuPatientVisit() {
        int userInput;
        System.out.println("1. Add a patient visit.");
        System.out.println("2. Delete patient visit.");
        System.out.println("3. Show all patient visits.");
        System.out.println("4. Edit patient visits.");
        System.out.println("5. Exit.");
        userInput = inputNumChecker.execute(1, 5);
        patientVisitActions(userInput);
        System.out.println();
        return userInput;
    }

    private static void patientVisitActions(int num) {
        switch (num) {
            case 1 -> addPatientVisit.execute();
            case 2 -> deletePatientVisitUIAction.execute();
            case 3 -> showAllPatientVisitUIAction.execute();
            case 4 -> editPatientVisitUIAction.execute();
            case 5 -> exitVisit.execute();
        }
    }
}