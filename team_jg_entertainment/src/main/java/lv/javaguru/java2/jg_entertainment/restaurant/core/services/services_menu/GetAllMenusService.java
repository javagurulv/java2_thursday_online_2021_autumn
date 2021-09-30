package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.GetAllMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.GetAllMenusResponse;

import java.util.List;

public class GetAllMenusService {

    private DatabaseMenu databaseMenu;

    public GetAllMenusService(DatabaseMenu databaseMenu) {
        this.databaseMenu = databaseMenu;
    }

    public GetAllMenusResponse execute(GetAllMenusRequest request) {
        List<Menu> menus = databaseMenu.getAllMenus();
        return new GetAllMenusResponse(menus);
    }

}