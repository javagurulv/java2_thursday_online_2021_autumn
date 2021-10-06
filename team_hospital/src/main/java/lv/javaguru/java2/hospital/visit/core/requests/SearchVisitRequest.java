package lv.javaguru.java2.hospital.visit.core.requests;

import lv.javaguru.java2.hospital.visit.core.requests.Ordering;
import lv.javaguru.java2.hospital.visit.core.requests.Paging;

import java.util.Date;

public class SearchVisitRequest {

    private Long visitId;
    private Long doctorId;
    private Long patientId;
    private Date visitDate;

    private Ordering ordering;
    private Paging paging;

    public SearchVisitRequest(Long visitId, Long doctorId, Long patientId, Date visitDate) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
    }

    public SearchVisitRequest(Long visitId, Long doctorId, Long patientId, Date visitDate, Ordering ordering) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.ordering = ordering;
    }

    public SearchVisitRequest(Long visitId, Long doctorId, Long patientId, Date visitDate, Paging paging) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.paging = paging;
    }

    public SearchVisitRequest(Long visitId, Long doctorId, Long patientId, Date visitDate, Ordering ordering, Paging paging) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.ordering = ordering;
        this.paging = paging;
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

    public Date getVisitDate() {
        return visitDate;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
