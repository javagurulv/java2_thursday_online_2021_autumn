package lv.javaguru.java2.hospital.prescription.core.requests;

public class EditPrescriptionRequest {
    private Long prescriptionID;
    private String editPrescriptionEnum;
    private String changes;

    public EditPrescriptionRequest(Long prescriptionID, String editPrescriptionEnum, String changes) {
        this.prescriptionID = prescriptionID;
        this.editPrescriptionEnum = editPrescriptionEnum;
        this.changes = changes;
    }

    public Long getPrescriptionID() {
        return prescriptionID;
    }

    public String getEditPrescriptionEnum() {
        return editPrescriptionEnum;
    }

    public String getChanges() {
        return changes;
    }
}
