package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus;

public class SearchMenusRequest {

    private String title;
    private String description;

    private OrderingMenu orderingMenu;
    private PagingMenu pagingMenu;

    public SearchMenusRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public SearchMenusRequest(String title, String description, OrderingMenu orderingMenu) {
        this.title = title;
        this.description = description;
        this.orderingMenu = orderingMenu;

    }

    public SearchMenusRequest(String title, String description, PagingMenu pagingMenu) {
        this.title = title;
        this.description = description;
        this.pagingMenu = pagingMenu;
    }

    public SearchMenusRequest(String title, String description, OrderingMenu orderingMenu, PagingMenu pagingMenu) {
        this.title = title;
        this.description = description;
        this.orderingMenu = orderingMenu;
        this.pagingMenu = pagingMenu;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTitleProvided() {
        return this.title != null && !this.title.isEmpty();
    }

    public boolean isDescriptionProvided() {
        return this.description != null && !this.description.isEmpty();
    }

    public OrderingMenu getOrderingMenu() {
        return orderingMenu;
    }

    public PagingMenu getPagingMenu() {
        return pagingMenu;
    }
}
