package lv.javaguru.java2.hospital.prescription.core.requests;

public class EditPrescriptionRequest {
    private String prescriptionID;
    private String editPrescriptionEnum;
    private String changes;

    public EditPrescriptionRequest() {
    }

    public EditPrescriptionRequest(String prescriptionID, String editPrescriptionEnum, String changes) {
        this.prescriptionID = prescriptionID;
        this.editPrescriptionEnum = editPrescriptionEnum;
        this.changes = changes;
    }

    public String getPrescriptionID() {
        return prescriptionID;
    }

    public String getEditPrescriptionEnum() {
        return editPrescriptionEnum;
    }

    public String getChanges() {
        return changes;
    }

    public void setPrescriptionID(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public void setEditPrescriptionEnum(String editPrescriptionEnum) {
        this.editPrescriptionEnum = editPrescriptionEnum;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
