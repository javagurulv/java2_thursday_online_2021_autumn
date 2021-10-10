package lv.javaguru.java2.hospital.visit.core.requests;

public class EditVisitRequest {

    private Long visitID;
    private EditVisitEnum editEnums;
    private String changes;

    public EditVisitRequest(Long visitID, EditVisitEnum editEnums, String changes) {
        this.visitID = visitID;
        this.editEnums = editEnums;
        this.changes = changes;
    }

    public Long getVisitID() {
        return visitID;
    }

    public EditVisitEnum getEditEnums() {
        return editEnums;
    }

    public String getChanges() {
        return changes;
    }
}
