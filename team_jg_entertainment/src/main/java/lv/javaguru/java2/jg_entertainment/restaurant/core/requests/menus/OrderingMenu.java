package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus;

public class OrderingMenu {

    private String orderBy;
    private String orderDirection;

    public OrderingMenu(String orderBy, String orderDirection) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }
}


