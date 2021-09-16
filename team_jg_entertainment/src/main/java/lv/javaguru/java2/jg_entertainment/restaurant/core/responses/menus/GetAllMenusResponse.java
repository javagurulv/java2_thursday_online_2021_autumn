package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus;

import lv.javaguru.java2.jg_entertainment.Menu;

import java.util.List;

public class GetAllMenusResponse {
    private List<Menu> menus;

    public GetAllMenusResponse(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
