package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus;

public class UpdateMenuRequest {

    private Long menuId;
    private String newTitle;
    private String newDescription;
    private double newPrice;

    public UpdateMenuRequest() {
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

}
