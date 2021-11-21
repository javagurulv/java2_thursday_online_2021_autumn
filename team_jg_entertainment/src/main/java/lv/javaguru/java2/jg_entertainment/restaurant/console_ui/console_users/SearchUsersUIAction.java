package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.SearchUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.SearchUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.SearchUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchUsersUIAction implements UserUIAction {

    @Autowired private SearchUsersService searchUsersService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter user name: ");
        String name = scanner.nextLine();
        System.out.println("Please, enter user surname: ");
        String surname = scanner.nextLine();

        System.out.println("Enter orderBy (name||surname): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASCENDING || DESCENDING): ");
        String orderDirection = scanner.nextLine();

        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());

        Paging paging = new Paging(pageNumber, pageSize);

        SearchUsersRequest request = new SearchUsersRequest(name, surname, ordering, paging);
        SearchUsersResponse response = searchUsersService.execute(request);

        if (response.hasError()) {
            response.getErrorsList().forEach(coreError -> System.out.println("Errors: " +
                    coreError.getField() + " " + coreError.getMessageError()));
        } else {
            System.out.println(response.getUsers());
        }
    }
}
