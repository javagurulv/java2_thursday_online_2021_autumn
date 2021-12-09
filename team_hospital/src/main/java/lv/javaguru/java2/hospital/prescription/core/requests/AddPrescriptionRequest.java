package lv.javaguru.java2.hospital.prescription.core.requests;

public class AddPrescriptionRequest {

    private String doctorId;
    private String patientId;
    private String medicationName;
    private String quantity;

    public AddPrescriptionRequest() {
    }

    public AddPrescriptionRequest(String doctorId, String patientId, String medicationName, String quantity) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medicationName = medicationName;
        this.quantity = quantity;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
