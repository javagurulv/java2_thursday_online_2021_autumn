package lv.javaguru.java2.hospital.doctor.core.responses;

import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public class GetDoctorResponse extends CoreResponse{

    private Doctor doctor;

    public GetDoctorResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetDoctorResponse(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
