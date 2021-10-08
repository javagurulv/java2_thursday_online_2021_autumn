package lv.javaguru.java2.hospital.patient.core.requests;

public class PatientOrdering {
    private String orderBy;
    private String orderDirection;

    public PatientOrdering(String orderBy, String orderDirection) {
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
