package lv.javaguru.java2.hospital.visit.core.requests;

public class EditVisitRequest {

    private String visitID;
    private String fieldToChange;
    private String changes;

    public EditVisitRequest() {
    }

    public EditVisitRequest(String visitID, String fieldToChange, String changes) {
        this.visitID = visitID;
        this.fieldToChange = fieldToChange;
        this.changes = changes;
    }

    public String getVisitID() {
        return visitID;
    }

    public String getFieldToChange() {
        return fieldToChange;
    }

    public String getChanges() {
        return changes;
    }

    public void setVisitID(String visitID) {
        this.visitID = visitID;
    }

    public void setFieldToChange(String fieldToChange) {
        this.fieldToChange = fieldToChange;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
