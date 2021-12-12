package lv.javaguru.java2.oddJobs.console_ui.remove;


import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.remove.RemoveSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveSpecialistUIAction implements UIAction {

    @Autowired
    private RemoveSpecialistService deleteSpecialistService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID");
        long specialistId = Long.parseLong(scanner.nextLine());

        System.out.println(("Enter specialist name"));
        String specialistName = scanner.nextLine();

        System.out.println("Enter specialist surname");
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
