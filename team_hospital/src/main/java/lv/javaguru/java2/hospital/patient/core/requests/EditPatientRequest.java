package lv.javaguru.java2.hospital.patient.core.requests;

public class EditPatientRequest {
    private String patientID;
    private String userInputEnum;
    private String changes;

    public EditPatientRequest() {
    }

    public EditPatientRequest(String patientID, String userInputEnum, String changes) {
        this.patientID = patientID;
        this.userInputEnum = userInputEnum;
        this.changes = changes;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getUserInputEnum() {
        return userInputEnum;
    }

    public String getChanges() {
        return changes;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setUserInputEnum(String userInputEnum) {
        this.userInputEnum = userInputEnum;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
