package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.SearchVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.SearchVisitorsService;

import java.util.Scanner;

public class SearchVisitorsUIAction implements RestaurantUIAction {

    private SearchVisitorsService searchVisitorsService;

    public SearchVisitorsUIAction(SearchVisitorsService searchVisitorsService) {
        this.searchVisitorsService = searchVisitorsService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter visitors name: ");
        String name = scanner.nextLine();
        System.out.println("Please, enter visitors surname: ");
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

        SearchVisitorsRequest request = new SearchVisitorsRequest(name, surname, ordering, paging);
        SearchVisitorsResponse response = searchVisitorsService.execute(request);

        if (response.hasError()) {
            response.getErrorsList().forEach(coreError -> System.out.println("Errors: " +
                    coreError.getField() + " " + coreError.getMessageError()));
        } else {
            System.out.println(response.getVisitors());
            ///response.getVisitors().forEach(Visitors::toString);
        }
    }
}
