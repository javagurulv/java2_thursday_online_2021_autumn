package lv.javaguru.java2.hospital.visits.core.request;

public class EditPatientVisitRequest {

    private Long visitID;
    private int userInput;
    private String changes;

    public EditPatientVisitRequest(Long visitID, int userInput, String changes) {
        this.visitID = visitID;
        this.userInput = userInput;
        this.changes = changes;
    }

    public Long getVisitID() {
        return visitID;
    }

    public int getUserInput() {
        return userInput;
    }

    public String getChanges() {
        return changes;
    }
}
