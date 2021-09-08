package lv.javaguru.java2.hospital.doctor.core.responses;

import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public class AddDoctorResponse extends CoreResponse {

    private Doctor newDoctor;

    public AddDoctorResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddDoctorResponse(Doctor newDoctor) {
        this.newDoctor = newDoctor;
    }

    public Doctor getNewDoctor() {
        return newDoctor;
    }
}
