package lv.javaguru.java2.hospital.doctor.core.requests;

public class DoctorOrdering {

    private String orderBy;
    private String orderDirection;

    public DoctorOrdering(String orderBy, String orderDirection) {
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
