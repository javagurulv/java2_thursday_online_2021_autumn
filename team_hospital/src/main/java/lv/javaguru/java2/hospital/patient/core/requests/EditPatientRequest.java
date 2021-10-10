package lv.javaguru.java2.hospital.patient.core.requests;

public class EditPatientRequest {
    private final Long patientID;
    private final EditPatientEnum enums;
    private final String changes;

    public EditPatientRequest(Long patientID, EditPatientEnum enums, String changes) {
        this.patientID = patientID;
        this.enums = enums;
        this.changes = changes;
    }

    public Long getPatientID() {
        return patientID;
    }

    public EditPatientEnum getEnums() {
        return enums;
    }

    public String getChanges() {
        return changes;
    }
}
