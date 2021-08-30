package lv.javaguru.java2.oddJobs.console_ui;

import lv.javaguru.java2.oddJobs.Specialist;
import lv.javaguru.java2.oddJobs.services.AddSpecialistService;

import java.util.Scanner;

public class AddSpecialistUIAction implements UIAction {
    private AddSpecialistService addSpecialistService;
    public AddSpecialistUIAction(AddSpecialistService addSpecialistService){this.addSpecialistService=addSpecialistService;};


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
        String specialistName = scanner.nextLine();
        System.out.println("Enter your surname");
        String specialistSurname = scanner.nextLine();
        System.out.println("Enter your profession");
        String specialistProfession = scanner.nextLine();
        Specialist specialist = new Specialist(specialistName, specialistSurname, specialistProfession);
        addSpecialistService.execute(specialistName,specialistSurname,specialistProfession);
        System.out.println("Registration OK");
    }
}
