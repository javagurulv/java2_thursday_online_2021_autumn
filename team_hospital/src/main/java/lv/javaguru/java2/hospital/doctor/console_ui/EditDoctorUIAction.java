package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.services.DoctorExistsService;
import lv.javaguru.java2.hospital.doctor.services.EditDoctorService;

import java.util.Scanner;

public class EditDoctorUIAction implements DoctorUIActions {
    private final EditDoctorService editDoctor;
    private final DoctorExistsService doctorExists;

    public EditDoctorUIAction(EditDoctorService editDoctor, DoctorExistsService doctorExists) {
        this.editDoctor = editDoctor;
        this.doctorExists = doctorExists;
    }

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        int id = getUserInput.getUserNumericInput("Please, enter the lv.javaguru.java2.hospital.doctor's id: ");
        if (doctorExists.execute(id)) {
            printEditMenu();
            int userInput = getUserInput.getUserNumericInput("Enter edit menu number: ");
            editAction("", id, userInput);
        } else {
            System.out.println("Can't find lv.javaguru.java2.hospital.doctor with such an Id.");
        }

    }

    private void printEditMenu() {
        System.out.println("What information you would like to edit? ");
        System.out.println("1. Name");
        System.out.println("2. Surname");
        System.out.println("3. Speciality");
    }

    private void editAction(String changes, int id, int userInput) {
        Scanner scanner = new Scanner(System.in);
        if (userInput == 1 || userInput == 2) {
            System.out.println("Enter info for change: ");
            changes = scanner.nextLine();
        }
        editDoctor.execute(id, userInput, changes);
        System.out.println("Changes are done!");
    }
}
