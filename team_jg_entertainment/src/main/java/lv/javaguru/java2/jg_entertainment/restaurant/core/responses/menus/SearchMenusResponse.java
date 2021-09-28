package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;

import java.util.List;

public class SearchMenusResponse extends CoreResponse {

    private List<Menu> menus;

    public SearchMenusResponse(List<Menu> menus, List<CoreError> errors) {
        super(errors);
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
