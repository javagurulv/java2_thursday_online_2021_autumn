package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;

import java.util.List;

public class GetMenuResponse extends CoreResponse {

    private Menu menu;

    public GetMenuResponse(Menu menu) {
        this.menu = menu;
    }

    public GetMenuResponse(List<CoreError> errors) {
        super(errors);
    }

    public Menu getMenu() {
        return menu;
    }
}
