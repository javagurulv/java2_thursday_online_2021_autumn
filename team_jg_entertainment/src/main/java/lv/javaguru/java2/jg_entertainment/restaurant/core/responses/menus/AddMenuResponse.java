package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus;

import lv.javaguru.java2.jg_entertainment.Menu;

public class AddMenuResponse {
    private Menu newMenu;

    public AddMenuResponse(Menu newMenu) {
        this.newMenu = newMenu;
    }

    public Menu getNewMenu() {
        return newMenu;
    }
}