package lv.javaguru.java2.hospital.visit.core.requests;

public class EditVisitRequest {

    private String visitID;
    private String editEnums;
    private String changes;

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
}
