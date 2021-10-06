package lv.javaguru.java2.hospital.visit.core.requests;

public class EditVisitRequest {

    private Long visitID;
    private int userInput;
    private String changes;

    public EditVisitRequest(Long visitID, int userInput, String changes) {
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
