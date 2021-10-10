package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.OrderingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.PagingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.SearchMenusResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.SearchMenusService;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchMenusUIAction implements UIAction {

   @Autowired private SearchMenusService searchMenusService;

//    public SearchMenusUIAction(SearchMenusService searchMenusService) {
//        this.searchMenusService = searchMenusService;
//    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu title: ");
        String title = scanner.nextLine();
        System.out.println("Enter menu description: ");
        String description = scanner.nextLine();

        System.out.println("Enter orderBy (title||description): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASCENDING||DESCENDING): ");
        String orderDirection = scanner.nextLine();
        OrderingMenu orderingMenu = new OrderingMenu(orderBy, orderDirection);

        System.out.println("Enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        PagingMenu pagingMenu = new PagingMenu(pageNumber, pageSize);

        SearchMenusRequest request = new SearchMenusRequest(title, description);
        SearchMenusResponse response = searchMenusService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getMenus().forEach(Menu::toString);
        }
    }

}
