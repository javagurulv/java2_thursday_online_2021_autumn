package lv.javaguru.java2.hospital.prescription.core.requests;

public class DeletePrescriptionRequest {

    private String prescriptionIdToDelete;

    public DeletePrescriptionRequest() {
    }

    public DeletePrescriptionRequest(String prescriptionIdToDelete) {
        this.prescriptionIdToDelete = prescriptionIdToDelete;
    }

    public String getPrescriptionIdToDelete() {
        return prescriptionIdToDelete;
    }

    public void setPrescriptionIdToDelete(String prescriptionIdToDelete) {
        this.prescriptionIdToDelete = prescriptionIdToDelete;
    }
}
