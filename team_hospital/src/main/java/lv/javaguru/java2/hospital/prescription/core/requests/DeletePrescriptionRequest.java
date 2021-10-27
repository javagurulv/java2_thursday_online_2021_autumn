package lv.javaguru.java2.hospital.prescription.core.requests;

public class DeletePrescriptionRequest {

    private Long prescriptionIdToDelete;

    public DeletePrescriptionRequest(Long prescriptionIdToDelete) {
        this.prescriptionIdToDelete = prescriptionIdToDelete;
    }

    public Long getPrescriptionIdToDelete() {
        return prescriptionIdToDelete;
    }
}
