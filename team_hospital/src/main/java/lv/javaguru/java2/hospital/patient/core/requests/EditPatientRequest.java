package lv.javaguru.java2.hospital.patient.core.requests;

public class EditPatientRequest {
    private final Long patientID;
    private final Integer userInput;
    private final String changes;

    public EditPatientRequest(Long patientID, Integer userInput, String changes) {
        this.patientID = patientID;
        this.userInput = userInput;
        this.changes = changes;
    }

    public Long getPatientID() {
        return patientID;
    }

    public Integer getUserInput() {
        return userInput;
    }

    public String getChanges() {
        return changes;
    }
}
