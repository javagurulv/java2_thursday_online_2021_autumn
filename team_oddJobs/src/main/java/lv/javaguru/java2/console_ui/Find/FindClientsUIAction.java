package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.console_ui.Exit.ExitMenuUIAction;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Find.FindClientsRequest;
import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.requests.Find.Paging;
import lv.javaguru.java2.core.responce.Find.FindClientsResponse;
import lv.javaguru.java2.services.Find.FindClientsService;

import java.util.Scanner;

public class FindClientsUIAction implements UIAction {


    private FindClientsService findClientsService;
    private ExitMenuUIAction exitUIAction;

    public FindClientsUIAction(FindClientsService findClientsService) {
        this.findClientsService = findClientsService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("provide ID");
        long clientId = scanner.nextLong();

        System.out.println("provide Name");
        String clientName = scanner.next();

        System.out.println("provide Surname");
        String clientSurname = scanner.next();

        System.out.println("Enter orderBy (clientName || clientSurname ): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASCENDING||DESCENDING): ");
        String orderDirection = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        Paging paging = new Paging(pageNumber, pageSize);

        FindClientsRequest request = new FindClientsRequest(clientId, clientName, clientSurname);
        FindClientsResponse response = findClientsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println(response.getClient());
        }
    }
}
