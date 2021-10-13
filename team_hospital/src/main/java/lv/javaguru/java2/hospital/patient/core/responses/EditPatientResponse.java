package lv.javaguru.java2.hospital.patient.core.responses;

import java.util.List;

public class EditPatientResponse extends CoreResponse {
    private Long patientID;
    private Enum userInput;
    private String changes;

    public EditPatientResponse(List<CoreError> errors) {
        super(errors);
    }

    public EditPatientResponse(Long patientID, Enum userInput, String changes) {
        this.patientID = patientID;
        this.userInput = userInput;
        this.changes = changes;
    }

    public Long getPatientID() {
        return patientID;
    }

    public Enum getUserInput() {
        return userInput;
    }

    public String getChanges() {
        return changes;
    }

}
