package lv.javaguru.java2.hospital.visit.core.requests;

public class SearchVisitRequest {

    private String visitId;
    private String doctorId;
    private String patientId;
    private String visitDate;

    private VisitOrdering visitOrdering;
    private VisitPaging visitPaging;

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
}
