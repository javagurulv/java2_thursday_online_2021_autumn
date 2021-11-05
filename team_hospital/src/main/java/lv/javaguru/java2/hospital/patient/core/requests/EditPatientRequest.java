package lv.javaguru.java2.hospital.patient.core.requests;

public class EditPatientRequest {
    private final String patientID;
    private final String userInputEnum;
    private final String changes;

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
}
