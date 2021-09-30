package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;

import java.util.List;

public class AddMenuResponse extends CoreResponse {

    private Menu newMenu;

    public AddMenuResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddMenuResponse(Menu newMenu) {
        this.newMenu = newMenu;
    }

    public Menu getNewMenu() {
        return newMenu;
    }
}