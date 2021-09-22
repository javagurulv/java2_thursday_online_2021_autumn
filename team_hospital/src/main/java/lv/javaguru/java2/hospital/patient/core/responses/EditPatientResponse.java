package lv.javaguru.java2.hospital.patient.core.responses;

import java.util.List;

public class EditPatientResponse extends CoreResponse {
    private String patientID;
    private String userInput;
    private String changes;
    private boolean trueOrNot;

    public EditPatientResponse(List<CoreError> errors) {
        super(errors);
    }

    public EditPatientResponse(String patientID, String userInput, String changes, boolean trueOrNot) {
        this.patientID = patientID;
        this.userInput = userInput;
        this.changes = changes;
        this.trueOrNot = trueOrNot;
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

    public boolean isTrueOrNot() {
        return trueOrNot;
    }
}
