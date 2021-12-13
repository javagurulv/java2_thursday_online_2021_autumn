package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;

import java.util.List;

public class UpdateMenuResponse extends CoreResponse{

    private Menu updateMenu;

    public UpdateMenuResponse(Menu updateMenu) {
        this.updateMenu = updateMenu;
    }

    public UpdateMenuResponse(List<CoreError> errors) {
        super(errors);
    }

    public Menu getUpdateMenu() {
        return updateMenu;
    }
}
