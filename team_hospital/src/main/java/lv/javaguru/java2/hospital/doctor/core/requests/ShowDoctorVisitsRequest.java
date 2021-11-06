package lv.javaguru.java2.hospital.doctor.core.requests;

public class ShowDoctorVisitsRequest {

    private Long doctorId;

    public ShowDoctorVisitsRequest(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() {
        return doctorId;
    }
}
