package lv.javaguru.java2.hospital.doctor.core.requests;

public class EditDoctorRequest {

    private long doctorId;
    private int userInput;
    private String changes;

    public EditDoctorRequest(long doctorId, int userInput, String changes) {
        this.doctorId = doctorId;
        this.userInput = userInput;
        this.changes = changes;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public int getUserInput() {
        return userInput;
    }

    public String getChanges() {
        return changes;
    }
}