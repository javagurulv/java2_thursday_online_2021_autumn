package lv.javaguru.java2.hospital.patient.core.requests;

public class EditPatientRequest {
    private String patientID;
    private String fieldToChange;
    private String changes;

    public EditPatientRequest() {
    }

    public EditPatientRequest(String patientID, String fieldToChange, String changes) {
        this.patientID = patientID;
        this.fieldToChange = fieldToChange;
        this.changes = changes;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getFieldToChange() {
        return fieldToChange;
    }

    public String getChanges() {
        return changes;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setFieldToChange(String fieldToChange) {
        this.fieldToChange = fieldToChange;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
