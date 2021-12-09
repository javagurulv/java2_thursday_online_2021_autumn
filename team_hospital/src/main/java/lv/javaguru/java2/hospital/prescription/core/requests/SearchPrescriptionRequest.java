package lv.javaguru.java2.hospital.prescription.core.requests;

public class SearchPrescriptionRequest {

    private Long prescriptionId;
    private Long doctorId;
    private Long patientId;

    private PrescriptionOrdering prescriptionOrdering;
    private PrescriptionPaging prescriptionPaging;

    public SearchPrescriptionRequest() {
    }

    public SearchPrescriptionRequest(Long prescriptionId, Long doctorId, Long patientId) {
        this.prescriptionId = prescriptionId;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public SearchPrescriptionRequest(Long prescriptionId, Long doctorId, Long patientId, PrescriptionPaging prescriptionPaging) {
        this.prescriptionId = prescriptionId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.prescriptionPaging = prescriptionPaging;
    }

    public SearchPrescriptionRequest(Long prescriptionId, Long doctorId, Long patientId, PrescriptionOrdering prescriptionOrdering) {
        this.prescriptionId = prescriptionId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.prescriptionOrdering = prescriptionOrdering;
    }

    public SearchPrescriptionRequest(Long prescriptionId, Long doctorId, Long patientId, PrescriptionOrdering prescriptionOrdering, PrescriptionPaging prescriptionPaging) {
        this.prescriptionId = prescriptionId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.prescriptionOrdering = prescriptionOrdering;
        this.prescriptionPaging = prescriptionPaging;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public boolean isPrescriptionIdProvided() {
        return this.prescriptionId != null;
    }

    public boolean isDoctorIdProvided() {
        return this.doctorId != null;
    }

    public boolean isPatientIdProvided() {
        return this.patientId != null;
    }

    public PrescriptionPaging getPrescriptionPaging() {
        return prescriptionPaging;
    }

    public PrescriptionOrdering getPrescriptionOrdering() {
        return prescriptionOrdering;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setPrescriptionOrdering(PrescriptionOrdering prescriptionOrdering) {
        this.prescriptionOrdering = prescriptionOrdering;
    }

    public void setPrescriptionPaging(PrescriptionPaging prescriptionPaging) {
        this.prescriptionPaging = prescriptionPaging;
    }
}
