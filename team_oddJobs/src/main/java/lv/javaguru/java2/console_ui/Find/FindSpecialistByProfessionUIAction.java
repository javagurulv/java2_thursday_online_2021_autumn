package lv.javaguru.java2.console_ui.Find;


import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Find.FindSpecialistByProfessionRequest;
import lv.javaguru.java2.core.responce.Find.FindSpecialistByProfessionResponse;
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

        FindSpecialistByProfessionRequest request = new FindSpecialistByProfessionRequest(profession);
        FindSpecialistByProfessionResponse response = findSpecialistByProfessionService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));

        }
    }
}