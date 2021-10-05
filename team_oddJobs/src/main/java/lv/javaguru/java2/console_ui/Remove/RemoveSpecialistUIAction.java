package lv.javaguru.java2.console_ui.Remove;


import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Remove.RemoveSpecialistRequest;
import lv.javaguru.java2.core.responce.Remove.RemoveSpecialistResponse;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;
import lv.javaguru.java2.services.Remove.RemoveSpecialistService;

import java.util.Scanner;

@DIComponent
public class RemoveSpecialistUIAction implements UIAction {

    @DIDependency
    private RemoveSpecialistService deleteSpecialistService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID");
        long specialistId = Long.parseLong(scanner.nextLine());

        System.out.println(("Enter specialist name (case sensitive)"));
        String specialistName = scanner.nextLine();

        System.out.println("Enter specialist surname(case sensitive)");
        String specialistSurname = scanner.nextLine();

        RemoveSpecialistRequest request = new RemoveSpecialistRequest(specialistId, specialistName, specialistSurname);
        RemoveSpecialistResponse removeSpecialistResponse = deleteSpecialistService.execute(request);

        if (removeSpecialistResponse.hasErrors()) {
            removeSpecialistResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            if (removeSpecialistResponse.isSpecialistRemoved()) {
                System.out.println("Your specialist account was deleted from list.");
            } else {
                System.out.println("Your specialist account was not deleted from list.");
            }
        }
        //System.out.println("Specialist deletion process" + removeSpecialistResponse);
    }
}
