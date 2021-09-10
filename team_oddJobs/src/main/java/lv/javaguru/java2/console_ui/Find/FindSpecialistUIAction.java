package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.console_ui.Exit.ExitMenuUIAction;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Find.FindSpecialistRequest;
import lv.javaguru.java2.core.responce.Find.FindSpecialistResponse;
import lv.javaguru.java2.services.Find.FindSpecialistService;

import java.util.Scanner;

public class FindSpecialistUIAction implements UIAction {

    private FindSpecialistService findSpecialistService;
    private ExitMenuUIAction exitMenuUIAction;

    public FindSpecialistUIAction(FindSpecialistService findSpecialistService) {
        this.findSpecialistService = findSpecialistService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("provide ID");
        long specialistId = scanner.nextLong();

        System.out.println("provide Name");
        String specialistName = scanner.next();

        System.out.println("provide Surname");
        String specialistSurname = scanner.next();

        FindSpecialistRequest request = new FindSpecialistRequest(specialistId, specialistName, specialistSurname);
        FindSpecialistResponse response = findSpecialistService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println(response.getSpecialists());
        }
    }
}
