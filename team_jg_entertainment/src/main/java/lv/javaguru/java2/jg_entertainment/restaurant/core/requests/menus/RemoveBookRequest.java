package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus;

public class RemoveBookRequest {
    private Long menuNumberToRemove;

    public RemoveBookRequest(Long menuNumberToRemove) {
        this.menuNumberToRemove = menuNumberToRemove;
    }

    public Long getMenuNumberToRemove() {
        return menuNumberToRemove;
    }
}