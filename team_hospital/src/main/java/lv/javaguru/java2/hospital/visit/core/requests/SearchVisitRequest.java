package lv.javaguru.java2.hospital.visit.core.requests;

public class SearchVisitRequest {

    private Long visitId;
    private Long doctorId;
    private Long patientId;
    private String visitDate;

    private VisitOrdering visitOrdering;
    private VisitPaging visitPaging;

    public SearchVisitRequest(Long visitId, Long doctorId, Long patientId, String visitDate) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
    }

    public SearchVisitRequest(Long visitId, Long doctorId, Long patientId, String visitDate, VisitOrdering visitOrdering) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.visitOrdering = visitOrdering;
    }

    public SearchVisitRequest(Long visitId, Long doctorId, Long patientId, String visitDate, VisitPaging visitPaging) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.visitPaging = visitPaging;
    }

    public SearchVisitRequest(Long visitId, Long doctorId, Long patientId, String visitDate, VisitOrdering visitOrdering, VisitPaging visitPaging) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.visitOrdering = visitOrdering;
        this.visitPaging = visitPaging;
    }

    public boolean isVisitIdProvided() {
        return this.visitId != null;
    }

    public boolean isDoctorIdProvided() {
        return this.doctorId != null;
    }

    public boolean isPatientIdProvided() {
        return this.visitId != null;
    }

    public boolean isDateProvided() {
        return this.visitDate != null;
    }

    public Long getVisitId() {
        return visitId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public VisitOrdering getOrdering() {
        return visitOrdering;
    }

    public VisitPaging getPaging() {
        return visitPaging;
    }
}
