package lv.javaguru.java2.hospital.doctor.core.requests;

public class DeleteDoctorRequest {

    private Long doctorIdToDelete;

    public DeleteDoctorRequest(Long doctorIdToDelete) {
        this.doctorIdToDelete = doctorIdToDelete;
    }

    public Long getDoctorIdToDelete() {
        return doctorIdToDelete;
    }
}
