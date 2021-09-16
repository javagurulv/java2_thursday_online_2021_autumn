package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.RemoveMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.RemoveMenuResponse;

public class RemoveMenuService {

    private DatabaseMenu databaseMenu;

    public RemoveMenuService(DatabaseMenu databaseMenu) {
        this.databaseMenu = databaseMenu;
    }

    public RemoveMenuResponse execute(RemoveMenuRequest request) {
        boolean isMenuRemoved = databaseMenu.deleteByNr(request.getMenuNumberToRemove());
        return new RemoveMenuResponse(isMenuRemoved);
    }

}