package lv.javaguru.java2.hospital.progmenu_hospital;

import lv.javaguru.java2.hospital.InputNumChecker;
import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.doctor.console_ui.DoctorProgramMenu;
import lv.javaguru.java2.hospital.patient.console_ui.PatientProgramMenu;
import lv.javaguru.java2.hospital.visit.console_ui.VisitProgramMenu;
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
                case 3 -> visitMenu();
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

    private static void doctorMenu() {
        DoctorProgramMenu doctorProgramMenu = applicationContext.getBean(DoctorProgramMenu.class);
        while (true) {
            doctorProgramMenu.printDoctorMenu();
            int doctorMenuNumber = doctorProgramMenu.getDoctorMenuNumberFromUser();
            doctorProgramMenu.executeSelectedMenuItem(doctorMenuNumber);
        }
    }

    private static void patientMenu() {
        PatientProgramMenu patientProgramMenu = applicationContext.getBean(PatientProgramMenu.class);
        while (true) {
            patientProgramMenu.printPatientMenu();
            int patientMenuNumber = patientProgramMenu.getPatientMenuNumberFromUser();
            patientProgramMenu.executeSelectedMenuItem(patientMenuNumber);
        }
    }

    private static void visitMenu() {
        VisitProgramMenu visitProgramMenu = applicationContext.getBean(VisitProgramMenu.class);
        while (true) {
            visitProgramMenu.printMenuPatientVisit();
            int visitMenuNumber = visitProgramMenu.getVisitMenuNumberFromUser();
            visitProgramMenu.executeSelectedMenuItem(visitMenuNumber);
        }
    }
}