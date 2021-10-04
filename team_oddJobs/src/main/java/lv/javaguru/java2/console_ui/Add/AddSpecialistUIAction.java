package lv.javaguru.java2.console_ui.Add;


import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Add.AddSpecialistRequest;
import lv.javaguru.java2.core.responce.Add.AddSpecialistResponse;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;
import lv.javaguru.java2.services.Add.AddSpecialistService;

import java.util.Scanner;

@DIComponent
public class AddSpecialistUIAction implements UIAction {
    @DIDependency
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

        AddSpecialistRequest addSpecialistRequest = new AddSpecialistRequest(name, surname, profession);
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
