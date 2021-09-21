package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.RemoveMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.RemoveMenuResponse;

import java.util.ArrayList;
import java.util.List;

public class RemoveMenuService {

    private DatabaseMenu databaseMenu;

    public RemoveMenuService(DatabaseMenu databaseMenu) {
        this.databaseMenu = databaseMenu;
    }

    public RemoveMenuResponse execute(RemoveMenuRequest request) {
        if (request.getMenuNumberToRemove() == null) {
            CoreError error = new CoreError("id", "Must not be empty!");
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
            return new RemoveMenuResponse(errors);
        }
        boolean isMenuRemoved = databaseMenu.deleteByNr(request.getMenuNumberToRemove());
        return new RemoveMenuResponse(isMenuRemoved);
    }

}