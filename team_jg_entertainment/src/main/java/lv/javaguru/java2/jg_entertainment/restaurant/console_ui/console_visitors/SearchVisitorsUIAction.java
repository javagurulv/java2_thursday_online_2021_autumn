package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseSearchVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceSearchVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class SearchVisitorsUIAction implements RestaurantUIAction {

    @DIDependency private ServiceSearchVisitors searchVisitorsService;

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

        SearchVisitorsRequest request = new SearchVisitorsRequest(name, surname, ordering, paging);//+telephone or id
        ResponseSearchVisitors response = searchVisitorsService.execute(request);

        if (response.hasError()) {
            response.getErrorsList().forEach(coreError -> System.out.println("Errors: " +
                    coreError.getField() + " " + coreError.getMessageError()));
        } else {
            System.out.println(response.getVisitors());
            ///response.getVisitors().forEach(Visitors::toString);
        }
    }
}
