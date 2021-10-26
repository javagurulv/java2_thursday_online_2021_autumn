package lv.javaguru.java2.hospital.prescription.core.requests;

public class AddPrescriptionRequest {

    private Long doctorId;
    private Long patientId;
    private String medicationName;
    private Integer quantity;

    public AddPrescriptionRequest(Long doctorId, Long patientId, String medicationName, Integer quantity) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medicationName = medicationName;
        this.quantity = quantity;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
