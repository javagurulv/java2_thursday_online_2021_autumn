package lv.javaguru.java2.hospital.doctor.core.requests;

public class EditDoctorRequest {

    private String doctorId;
    private String userInputEnum;
    private String changes;

    public EditDoctorRequest(String doctorId, String userInputEnum, String changes) {
        this.doctorId = doctorId;
        this.userInputEnum = userInputEnum;
        this.changes = changes;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getUserInputEnum() {
        return userInputEnum;
    }

    public String getChanges() {
        return changes;
    }
}
