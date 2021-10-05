package lv.javaguru.java2.hospital.progmenu_hospital;

import lv.javaguru.java2.hospital.InputNumChecker;
import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.dependency_injection.ApplicationContext;
import lv.javaguru.java2.hospital.dependency_injection.DIApplicationContextBuilder;
import lv.javaguru.java2.hospital.doctor.console_ui.*;
import lv.javaguru.java2.hospital.patient.console_ui.*;
import lv.javaguru.java2.hospital.visits.console_ui.*;
import lv.javaguru.java2.hospital.visits.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visits.core.services.DeleteVisitService;
import lv.javaguru.java2.hospital.visits.core.services.EditVisitService;
import lv.javaguru.java2.hospital.visits.core.services.ShowAllVisitService;
import lv.javaguru.java2.hospital.visits.core.services.validators.AddVisitValidator;
import lv.javaguru.java2.hospital.visits.core.services.validators.DeleteVisitValidator;
import lv.javaguru.java2.hospital.visits.core.services.validators.EditVisitValidator;


public class ProgMenuHospital {

    private static ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.hospital");

    /*private static final PatientApplicationContext patientApplicationContext = new PatientApplicationContext();*/
    /*private static final DoctorApplicationContext doctorApplicationContext = new DoctorApplicationContext();*/

    private static final InputNumChecker inputNumChecker = new InputNumChecker();

    private static final AddVisitUIAction addPatientVisit =
            new AddVisitUIAction(new AddVisitService
                    (applicationContext.getBean(PatientDatabase.class),
                            applicationContext.getBean(DoctorDatabase.class),
                            new VisitDatabaseImpl(),
                            new AddVisitValidator(applicationContext.getBean(PatientDatabase.class),
                                    applicationContext.getBean(DoctorDatabase.class))));

    private static final DeleteVisitUIAction DELETE_VISIT_UI_ACTION =
            new DeleteVisitUIAction(
                    new DeleteVisitService(new VisitDatabaseImpl(), new DeleteVisitValidator()));

    private static final VisitDatabaseImpl visitDatabase = new VisitDatabaseImpl();
    private static final ShowAllVisitService SHOW_ALL_VISIT_SERVICE =
            new ShowAllVisitService(visitDatabase);
    private static final ShowAllVisitUIAction showAllPatientVisitUIAction =
            new ShowAllVisitUIAction(SHOW_ALL_VISIT_SERVICE);
    private static final EditVisitValidator EDIT_VISIT_VALIDATOR = new EditVisitValidator();
    private static final EditVisitService EDIT_VISIT_SERVICE =
            new EditVisitService(visitDatabase, EDIT_VISIT_VALIDATOR);
    private static final EditVisitUIAction EDIT_VISIT_UI_ACTION =
            new EditVisitUIAction(EDIT_VISIT_SERVICE);
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
                AddPatientUIAction addPatientUIAction = applicationContext.getBean(AddPatientUIAction.class);
                addPatientUIAction.execute();
            }
            case 2 -> {
                ShowAllPatientsUIAction showAllPatientsUIAction = applicationContext.getBean(ShowAllPatientsUIAction.class);
                showAllPatientsUIAction.execute();
            }
            case 3 -> {
                FindPatientByIDUIAction findPatientByIDUIAction = applicationContext.getBean(FindPatientByIDUIAction.class);
                findPatientByIDUIAction.execute();
            }
            case 4 -> {
                DeletePatientUIAction deletePatientUIAction = applicationContext.getBean(DeletePatientUIAction.class);
                deletePatientUIAction.execute();
            }
            case 5 -> {
                EditPatientUIAction editPatientUIAction = applicationContext.getBean(EditPatientUIAction.class);
                editPatientUIAction.execute();
            }
            case 6 -> {
                SearchPatientsUIAction searchPatientsUIAction = applicationContext.getBean(SearchPatientsUIAction.class);
                searchPatientsUIAction.execute();
            }
        }
    }

    private static void doctorUserActions(int num) {
        switch (num) {
            case 1 -> {
                AddDoctorUIAction uiAction = applicationContext.getBean(AddDoctorUIAction.class);
                uiAction.execute();
            }
            case 2 -> {
                ShowAllDoctorsUIAction uiAction = applicationContext.getBean(ShowAllDoctorsUIAction.class);
                uiAction.execute();
            }
            case 3 -> {
                DeleteDoctorUIAction uiAction = applicationContext.getBean(DeleteDoctorUIAction.class);
                uiAction.execute();
            }
            case 4 -> {
                EditDoctorUIAction uiAction = applicationContext.getBean(EditDoctorUIAction.class);
                uiAction.execute();
            }
            case 5 -> {
                SearchDoctorsUIAction uiAction = applicationContext.getBean(SearchDoctorsUIAction.class);
                uiAction.execute();
            }
            case 6 -> {
                ExitDoctorUIAction uiAction = applicationContext.getBean(ExitDoctorUIAction.class);
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
            /*case 1 -> addPatientVisit.execute();*/
            case 2 -> DELETE_VISIT_UI_ACTION.execute();
            case 3 -> showAllPatientVisitUIAction.execute();
            case 4 -> EDIT_VISIT_UI_ACTION.execute();
            case 5 -> exitVisit.execute();
        }
    }
}