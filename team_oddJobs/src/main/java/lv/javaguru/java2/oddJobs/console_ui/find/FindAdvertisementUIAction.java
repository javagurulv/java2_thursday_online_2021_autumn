package lv.javaguru.java2.oddJobs.console_ui.find;


import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import lv.javaguru.java2.oddJobs.console_ui.exit.ExitMenuUIAction;
import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.response.find.FindAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.services.find.FindAdvertisementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindAdvertisementUIAction implements UIAction {

    @Autowired
    private FindAdvertisementsService findAdvertisementsService;

    @Autowired
    private ExitMenuUIAction exitMenuUIAction;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("provide ID");
        long advId = Long.parseLong(scanner.nextLine());

        System.out.println("provide Title");
        String advTitle = scanner.nextLine();

        System.out.println("Enter orderBy (advID || advTitle ): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASCENDING||DESCENDING): ");
        String orderDirection = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        Paging paging = new Paging(pageNumber, pageSize);

        FindAdvertisementRequest request = new FindAdvertisementRequest( advId,advTitle);
        FindAdvertisementResponse response = findAdvertisementsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getAdvertisements().forEach(System.out::println);
        }
    }
}
