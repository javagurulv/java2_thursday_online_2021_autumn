package lv.javaguru.java2.hospital.doctor.core.requests;

public class EditDoctorRequest {

    private Long doctorId;
    private String userInputEnum;
    private String changes;

    public EditDoctorRequest(Long doctorId, String userInputEnum, String changes) {
        this.doctorId = doctorId;
        this.userInputEnum = userInputEnum;
        this.changes = changes;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getUserInputEnum() {
        return userInputEnum;
    }

    public String getChanges() {
        return changes;
    }
}
