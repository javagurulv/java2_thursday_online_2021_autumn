package lv.javaguru.java2.hospital.progmenu_hospital;

import lv.javaguru.java2.hospital.InputNumChecker;
import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.doctor.console_ui.*;
import lv.javaguru.java2.hospital.patient.console_ui.*;
import lv.javaguru.java2.hospital.visit.console_ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProgMenuHospital {

    private static final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(HospitalConfiguration.class);

    private static final InputNumChecker inputNumChecker = new InputNumChecker();

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
        DoctorProgramMenu doctorProgramMenu = applicationContext.getBean(DoctorProgramMenu.class);
        while (true) {
            doctorProgramMenu.printDoctorMenu();
            int doctorMenuNumber = doctorProgramMenu.getDoctorMenuNumberFromUser();
            doctorProgramMenu.executeSelectedMenuItem(doctorMenuNumber);
        }
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
        System.out.println("5. Search patient visits.");
        System.out.println("6. Exit.");
        userInput = inputNumChecker.execute(1, 6);
        patientVisitActions(userInput);
        System.out.println();
        return userInput;
    }

    private static void patientVisitActions(int num) {
        switch (num) {
            case 1 -> {
                AddVisitUIAction addVisitUIAction = applicationContext.getBean(AddVisitUIAction.class);
                addVisitUIAction.execute();
            }
            case 2 -> {
                DeleteVisitUIAction deleteVisitUIAction = applicationContext.getBean(DeleteVisitUIAction.class);
                deleteVisitUIAction.execute();
            }
            case 3 -> {
                ShowAllVisitUIAction showAllVisitUIAction = applicationContext.getBean(ShowAllVisitUIAction.class);
                showAllVisitUIAction.execute();
            }
            case 4 -> {
                EditVisitUIAction editVisitUIAction = applicationContext.getBean(EditVisitUIAction.class);
                editVisitUIAction.execute();
            }
            case 5 -> {
                SearchVisitUIAction searchVisitUIAction = applicationContext.getBean(SearchVisitUIAction.class);
                searchVisitUIAction.execute();
            }
            case 6 -> {
                ExitVisitUIAction exitVisitUIAction = applicationContext.getBean(ExitVisitUIAction.class);
                exitVisitUIAction.execute();
            }
        }
    }
}