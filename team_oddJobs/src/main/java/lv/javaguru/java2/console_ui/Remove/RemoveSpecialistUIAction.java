package lv.javaguru.java2.console_ui.Remove;


import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Remove.RemoveSpecialistRequest;
import lv.javaguru.java2.core.responce.Remove.RemoveSpecialistResponse;
import lv.javaguru.java2.services.Remove.RemoveSpecialistService;

import java.util.Scanner;

public class RemoveSpecialistUIAction implements UIAction {

    private RemoveSpecialistService deleteSpecialistService;

    public RemoveSpecialistUIAction(RemoveSpecialistService deleteSpecialistService) {
        this.deleteSpecialistService = deleteSpecialistService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter specialist ID");
        long specialistId = Long.parseLong(scanner.nextLine());

        System.out.println(("Enter specialist name (case sensitive)"));
        String specialistName = scanner.nextLine();

        System.out.println("Enter specialist surname(case sensitive)");
        String specialistSurname = scanner.nextLine();

        RemoveSpecialistRequest request = new RemoveSpecialistRequest(specialistId, specialistName, specialistSurname);
        RemoveSpecialistResponse removeSpecialistResponse = deleteSpecialistService.execute(request);
        System.out.println("Specialist deletion process" + removeSpecialistResponse);
    }
}
