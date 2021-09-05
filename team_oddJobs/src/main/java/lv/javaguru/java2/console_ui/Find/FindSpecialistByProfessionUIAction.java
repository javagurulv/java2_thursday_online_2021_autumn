package lv.javaguru.java2.console_ui.Find;


import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Find.FindSpecialistByProfessionService;

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