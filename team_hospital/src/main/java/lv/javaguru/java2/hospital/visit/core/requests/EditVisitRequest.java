package lv.javaguru.java2.hospital.visit.core.requests;

public class EditVisitRequest {

    private String visitID;
    private String editEnums;
    private String changes;

    public EditVisitRequest() {
    }

    public EditVisitRequest(String visitID, String editEnums, String changes) {
        this.visitID = visitID;
        this.editEnums = editEnums;
        this.changes = changes;
    }

    public String getVisitID() {
        return visitID;
    }

    public String getEditEnums() {
        return editEnums;
    }

    public String getChanges() {
        return changes;
    }

    public void setVisitID(String visitID) {
        this.visitID = visitID;
    }

    public void setEditEnums(String editEnums) {
        this.editEnums = editEnums;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
