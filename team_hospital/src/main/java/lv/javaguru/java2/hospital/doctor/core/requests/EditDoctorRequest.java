package lv.javaguru.java2.hospital.doctor.core.requests;

public class EditDoctorRequest {

    private String doctorId;
    private String userInputEnum;
    private String changes;

    public EditDoctorRequest() {
    }

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

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setUserInputEnum(String userInputEnum) {
        this.userInputEnum = userInputEnum;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
