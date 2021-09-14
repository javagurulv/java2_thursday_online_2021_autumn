package lv.javaguru.java2.qwe.core.requests.data_requests;

public class OrderingRequest extends SecurityRequest {

    private final String orderBy;
    private final String orderDirection;

    public OrderingRequest(String orderBy, String orderDirection) {
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