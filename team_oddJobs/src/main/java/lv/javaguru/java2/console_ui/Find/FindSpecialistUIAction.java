package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.console_ui.Exit.ExitMenuUIAction;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Find.FindSpecialistRequest;
import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.requests.Find.Paging;
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

        System.out.println("Enter Name");
        String specialistName = scanner.nextLine();
        System.out.println("Enter Surname");
        String specialistSurname = scanner.nextLine();
        System.out.println("Enter Profession");
        String specialistProfession = scanner.nextLine();

        System.out.println("Enter orderBy (Name || Surname || Profession): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASCENDING||DESCENDING): ");
        String orderDirection = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy, orderDirection);


        System.out.println("Enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        Paging paging = new Paging(pageNumber, pageSize);

        FindSpecialistRequest request = new FindSpecialistRequest(specialistName, specialistSurname, specialistProfession, ordering, paging);
        FindSpecialistResponse response = findSpecialistService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getSpecialists().forEach(Specialist::toString);
        }
    }
}
