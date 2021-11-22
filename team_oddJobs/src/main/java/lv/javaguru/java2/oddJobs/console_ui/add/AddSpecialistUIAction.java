package lv.javaguru.java2.oddJobs.console_ui.add;


import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddSpecialistUIAction implements UIAction {
    @Autowired
    private AddSpecialistService addSpecialistService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.println("Enter your surname");
        String surname = scanner.nextLine();
        System.out.println("Enter your profession");
        String profession = scanner.nextLine();
        System.out.println("Enter your personal code");
        String personalCode = scanner.nextLine();
        System.out.println("Enter your city");
        String city = scanner.nextLine();

        AddSpecialistRequest addSpecialistRequest = new AddSpecialistRequest(name, surname, profession,personalCode,city);
        AddSpecialistResponse addSpecialistResponse = addSpecialistService.execute(addSpecialistRequest);

        if (addSpecialistResponse.hasErrors()) {
            addSpecialistResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New specialist id is " + addSpecialistResponse.getSpecialist().getSpecialistId());
            System.out.println("Specialist" + addSpecialistResponse.getSpecialist() + " created! ");
        }
    }
}
