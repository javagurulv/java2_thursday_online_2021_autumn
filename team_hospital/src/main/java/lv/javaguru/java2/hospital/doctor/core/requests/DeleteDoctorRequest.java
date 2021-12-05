package lv.javaguru.java2.hospital.doctor.core.requests;

public class DeleteDoctorRequest {

    private String doctorIdToDelete;

    public DeleteDoctorRequest() {
    }

    public DeleteDoctorRequest(String doctorIdToDelete) {
        this.doctorIdToDelete = doctorIdToDelete;
    }

    public String getDoctorIdToDelete() {
        return doctorIdToDelete;
    }
}
