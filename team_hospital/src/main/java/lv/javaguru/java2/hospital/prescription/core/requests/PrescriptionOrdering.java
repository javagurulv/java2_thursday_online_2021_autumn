package lv.javaguru.java2.hospital.prescription.core.requests;

public class PrescriptionOrdering {

    private String orderBy;
    private String orderDirection;

    public PrescriptionOrdering(String orderBy, String orderDirection) {
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
