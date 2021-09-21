package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus;

import java.util.List;

public class RemoveMenuResponse extends CoreResponse {

    private boolean menuRemoved;

    public RemoveMenuResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveMenuResponse(boolean menuRemoved) {
        this.menuRemoved = menuRemoved;
    }

    public boolean isMenuRemoved() {
        return menuRemoved;
    }
}