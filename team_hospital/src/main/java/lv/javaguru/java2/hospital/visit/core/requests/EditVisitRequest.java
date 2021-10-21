package lv.javaguru.java2.hospital.visit.core.requests;

public class EditVisitRequest {

    private Long visitID;
    private String editEnums;
    private String changes;

    public EditVisitRequest(Long visitID, String editEnums, String changes) {
        this.visitID = visitID;
        this.editEnums = editEnums;
        this.changes = changes;
    }

    public Long getVisitID() {
        return visitID;
    }

    public String getEditEnums() {
        return editEnums;
    }

    public String getChanges() {
        return changes;
    }
}
