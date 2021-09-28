package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables;

public class OrderingTable {

    private String orderBy;
    private String orderDirection;

    public OrderingTable(String orderBy, String orderDirection) {
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
