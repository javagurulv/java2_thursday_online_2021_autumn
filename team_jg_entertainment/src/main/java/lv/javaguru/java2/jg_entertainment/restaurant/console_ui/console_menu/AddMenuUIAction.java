package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import java.util.Scanner;

import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.AddMenuService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.AddMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.AddMenuRequest;

public class AddMenuUIAction implements UIAction {

    private AddMenuService addMenuService;

    public AddMenuUIAction(AddMenuService addMenuService) {
        this.addMenuService = addMenuService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu title: ");
        String menuTitle = scanner.nextLine();
        System.out.println("Enter menu description: ");
        String menuDescription = scanner.nextLine();
        System.out.println("Enter menu price: ");
        double menuPrice = scanner.nextDouble();
        AddMenuRequest request = new AddMenuRequest(menuTitle, menuDescription, menuPrice);
        AddMenuResponse response = addMenuService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New book id was: " + response.getNewMenu().getNumber());
            System.out.println("Your book was added to list.");
        }
     }
}