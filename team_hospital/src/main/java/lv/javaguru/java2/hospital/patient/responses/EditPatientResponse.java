package lv.javaguru.java2.hospital.patient.responses;

import java.util.List;

public class EditPatientResponse extends CoreResponse {
    private String patientID;
    private String userInput;
    private String changes;

    public EditPatientResponse(List<CoreError> errors) {
        super(errors);
    }

    public EditPatientResponse(String patientID, String userInput, String changes) {
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
