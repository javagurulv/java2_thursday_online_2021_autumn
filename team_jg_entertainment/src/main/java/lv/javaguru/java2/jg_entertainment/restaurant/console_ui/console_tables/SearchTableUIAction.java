package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.OrderingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.PagingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.SearchTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.SearchTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchTableUIAction implements UIAction {

    @Autowired private SearchTableService searchTableService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter title table: ");
        String title = scanner.nextLine();
        System.out.println("Enter orderBy (enter only title) table: ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASCENDING || DESCENDING): ");
        String orderDirection = scanner.nextLine();

        OrderingTable ordering = new OrderingTable(orderBy, orderDirection);

        System.out.println("Enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());

        PagingTable paging = new PagingTable(pageNumber, pageSize);

        SearchTableRequest request = new SearchTableRequest(title, ordering, paging);
        SearchTableResponse response = searchTableService.execute(request);

        if (response.hasError()) {
            response.getErrorsList().forEach(coreError -> System.out.println("Errors: " + coreError.getField()
                    + " " + coreError.getMessageError()));
        } else {
            System.out.println(response.getTables());
        }
    }
}
