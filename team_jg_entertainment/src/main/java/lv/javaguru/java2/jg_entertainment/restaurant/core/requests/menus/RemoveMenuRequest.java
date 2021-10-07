package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus;

public class RemoveMenuRequest {

    private Long menuNumberToRemove;

    public RemoveMenuRequest(Long menuNumberToRemove) {
        this.menuNumberToRemove = menuNumberToRemove;
    }

    public Long getMenuNumberToRemove() {
        return menuNumberToRemove;
    }
}