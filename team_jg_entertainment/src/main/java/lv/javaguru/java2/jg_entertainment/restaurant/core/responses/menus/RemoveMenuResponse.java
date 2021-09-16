package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus;

public class RemoveMenuResponse {

    private boolean menuRemoved;

    public RemoveMenuResponse(boolean menuRemoved) {
        this.menuRemoved = menuRemoved;
    }

    public boolean isMenuRemoved() {
        return menuRemoved;
    }
}