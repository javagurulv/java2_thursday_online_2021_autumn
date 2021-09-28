package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import lv.javaguru.java2.jg_entertainment.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.SearchMenusResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.SearchMenusService;

import java.util.Scanner;

public class SearchMenusUIAction implements UIAction {

    private SearchMenusService searchMenusService;

    public SearchMenusUIAction(SearchMenusService searchMenusService) {
        this.searchMenusService = searchMenusService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu title: ");
        String title = scanner.nextLine();
        System.out.println("Enter menu description: ");
        String description = scanner.nextLine();

        SearchMenusRequest request = new SearchMenusRequest(title, description);
        SearchMenusResponse response = searchMenusService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getMenus().forEach(Menu::toString);
        }
    }
}
