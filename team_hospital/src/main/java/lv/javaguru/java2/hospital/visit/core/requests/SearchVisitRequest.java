package lv.javaguru.java2.hospital.visit.core.requests;

public class SearchVisitRequest {

    private String visitId;
    private String doctorId;
    private String patientId;
    private String visitDate;
    private String orderBy;
    private String orderDirection;
    private String pageNumber;
    private String pageSize;

    private VisitOrdering visitOrdering;
    private VisitPaging visitPaging;

    public SearchVisitRequest() {
    }

    public SearchVisitRequest(String visitId, String doctorId, String patientId, String visitDate) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
    }

    public SearchVisitRequest(String visitId, String doctorId, String patientId, String visitDate, VisitOrdering visitOrdering) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.visitOrdering = visitOrdering;
    }

    public SearchVisitRequest(String visitId, String doctorId, String patientId, String visitDate, VisitPaging visitPaging) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.visitPaging = visitPaging;
    }

    public SearchVisitRequest(String visitId, String doctorId, String patientId, String visitDate, VisitOrdering visitOrdering, VisitPaging visitPaging) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.visitOrdering = visitOrdering;
        this.visitPaging = visitPaging;
    }

    public SearchVisitRequest(String visitId, String doctorId, String patientId, String visitDate,
                              String orderBy, String orderDirection, String pageNumber, String pageSize) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public boolean isVisitIdProvided() {
        return this.visitId != null && !this.visitId.isEmpty();
    }

    public boolean isDoctorIdProvided() {
        return this.doctorId != null && !this.doctorId.isEmpty();
    }

    public boolean isPatientIdProvided() {
        return this.patientId != null && !this.patientId.isEmpty();
    }

    public boolean isDateProvided() {
        return this.visitDate != null && !this.visitDate.isEmpty();
    }

    public String getVisitId() {
        return visitId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public VisitOrdering getVisitOrdering() {
        return visitOrdering;
    }

    public VisitPaging getVisitPaging() {
        return visitPaging;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public void setVisitOrdering(VisitOrdering visitOrdering) {
        this.visitOrdering = visitOrdering;
    }

    public void setVisitPaging(VisitPaging visitPaging) {
        this.visitPaging = visitPaging;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
