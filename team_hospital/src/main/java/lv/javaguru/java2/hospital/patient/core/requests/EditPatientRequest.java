package lv.javaguru.java2.hospital.patient.core.requests;

public class EditPatientRequest {
    private final Long patientID;
    private final SearchEnums enums;
    private final String changes;

    public EditPatientRequest(Long patientID, SearchEnums enums, String changes) {
        this.patientID = patientID;
        this.enums = enums;
        this.changes = changes;
    }

    public Long getPatientID() {
        return patientID;
    }

    public SearchEnums getEnums() {
        return enums;
    }

    public String getChanges() {
        return changes;
    }
}
