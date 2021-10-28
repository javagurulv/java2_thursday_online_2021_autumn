package lv.javaguru.java2.hospital.prescription.core.requests;

public class SearchPrescriptionRequest {

    private Long prescriptionId;
    private Long doctorId;
    private Long patientId;

    public SearchPrescriptionRequest(Long prescriptionId, Long doctorId, Long patientId) {
        this.prescriptionId = prescriptionId;
        this.doctorId = doctorId;
        this.patientId = patientId;
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

}
