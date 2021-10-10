package lv.javaguru.java2.hospital.visit.core.requests;

public class VisitOrdering {

    private String orderBy;
    private String orderDirection;

    public VisitOrdering(String orderBy, String orderDirection) {
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
