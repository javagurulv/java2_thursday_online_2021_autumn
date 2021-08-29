package lv.javaguru.java2.oddJobs.console_ui;

import lv.javaguru.java2.oddJobs.services.FindSpecialistByProfessionService;

import java.util.Scanner;

public class FindSpecialistByProfessionUIAction implements UIAction {
    private FindSpecialistByProfessionService findSpecialistByProfessionService;

    public FindSpecialistByProfessionUIAction(FindSpecialistByProfessionService findSpecialistByProfessionService) {
        this.findSpecialistByProfessionService = findSpecialistByProfessionService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter profession");
        String profession = scanner.nextLine();
        System.out.println("Specialists with " + profession + " profession has founded:");
        findSpecialistByProfessionService.execute(profession);
        System.out.println();

    }
}