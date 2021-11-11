package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.RemoveMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.RemoveMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.RemoveMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveMenuUIAction implements UIAction {

    @Autowired private RemoveMenuService deleteMenu;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        Long number = Long.parseLong(scanner.nextLine());
        RemoveMenuRequest request = new RemoveMenuRequest(number);
        RemoveMenuResponse response = deleteMenu.execute(request);

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
