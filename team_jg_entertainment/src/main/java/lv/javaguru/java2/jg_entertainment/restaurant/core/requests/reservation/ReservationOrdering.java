package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class ReservationOrdering {

    private String orderBy;
    private String orderDirection;

    public ReservationOrdering(String orderBy, String orderDirection) {
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
