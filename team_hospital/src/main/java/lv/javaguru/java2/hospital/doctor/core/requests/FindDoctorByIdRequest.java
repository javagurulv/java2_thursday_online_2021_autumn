package lv.javaguru.java2.hospital.doctor.core.requests;

public class FindDoctorByIdRequest {

    private long doctorId;

    public FindDoctorByIdRequest(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getDoctorId() {
        return doctorId;
    }
}
