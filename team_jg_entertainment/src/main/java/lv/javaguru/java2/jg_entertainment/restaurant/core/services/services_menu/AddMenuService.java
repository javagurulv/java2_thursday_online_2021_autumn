package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.AddMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.AddMenuResponse;

public class AddMenuService {

    private DatabaseMenu databaseMenu;

    public AddMenuService(DatabaseMenu databaseMenu) {
        this.databaseMenu = databaseMenu;
    }

    public AddMenuResponse execute(AddMenuRequest request) {
        Menu menu = new Menu(request.getTitle(), request.getDescription(), request.getPrice());
        databaseMenu.save(menu);
        return new AddMenuResponse(menu);
    }
}