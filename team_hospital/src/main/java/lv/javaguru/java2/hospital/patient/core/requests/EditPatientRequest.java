package lv.javaguru.java2.hospital.patient.core.requests;

public class EditPatientRequest {
    private final String patientID;
    private final String userInput;
    private final String changes;

    public EditPatientRequest(String patientID, String userInput, String changes) {
        this.patientID = patientID;
        this.userInput = userInput;
        this.changes = changes;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getUserInput() {
        return userInput;
    }

    public String getChanges() {
        return changes;
    }
}
