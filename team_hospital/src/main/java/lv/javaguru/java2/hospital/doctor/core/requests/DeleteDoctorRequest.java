package lv.javaguru.java2.hospital.doctor.core.requests;

public class DeleteDoctorRequest {

    private long doctorIdToDelete;

    public DeleteDoctorRequest(long doctorIdToDelete) {
        this.doctorIdToDelete = doctorIdToDelete;
    }

    public long getDoctorIdToDelete() {
        return doctorIdToDelete;
    }
}
