package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.RemoveMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.RemoveMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.RemoveMenuService;

import java.util.Scanner;

public class RemoveMenuUIAction implements UIAction {

    private RemoveMenuService removeMenuService;

    public RemoveMenuUIAction(RemoveMenuService removeMenuService) {
        this.removeMenuService = removeMenuService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        Long number = Long.parseLong(scanner.nextLine());
        RemoveMenuRequest request = new RemoveMenuRequest(number);
        RemoveMenuResponse response = removeMenuService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isMenuRemoved()) {
                System.out.println("That menu was removed from the order.");
            } else {
                System.out.println("That menu not removed from list.");
            }
        }
    }
}
