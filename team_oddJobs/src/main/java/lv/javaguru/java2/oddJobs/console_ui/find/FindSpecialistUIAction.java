package lv.javaguru.java2.oddJobs.console_ui.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.responce.find.FindSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.find.FindSpecialistService;
import lv.javaguru.java2.oddJobs.domain.Specialist;
import lv.javaguru.java2.oddJobs.console_ui.exit.ExitMenuUIAction;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindSpecialistUIAction implements UIAction {

    @Autowired
    private FindSpecialistService findSpecialistService;
    @Autowired
    private ExitMenuUIAction exitMenuUIAction;

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
