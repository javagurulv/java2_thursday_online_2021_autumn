package lv.javaguru.java2.hospital.patient.core.responses;

import java.util.List;

public class EditPatientResponse extends CoreResponse {
    private Long patientID;
    private Integer userInput;
    private String changes;
    private boolean trueOrNot;

    public EditPatientResponse(List<CoreError> errors) {
        super(errors);
    }

    public EditPatientResponse(Long patientID, Integer userInput, String changes, boolean trueOrNot) {
        this.patientID = patientID;
        this.userInput = userInput;
        this.changes = changes;
        this.trueOrNot = trueOrNot;
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

    public boolean isTrueOrNot() {
        return trueOrNot;
    }
}
