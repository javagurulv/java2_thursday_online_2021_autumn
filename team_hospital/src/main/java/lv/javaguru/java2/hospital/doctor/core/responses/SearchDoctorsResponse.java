package lv.javaguru.java2.hospital.doctor.core.responses;

import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public class SearchDoctorsResponse extends CoreResponse {

    private List<Doctor> doctors;

    public SearchDoctorsResponse(List<Doctor> doctors, List<CoreError> errors) {
        super(errors);
        this.doctors = doctors;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
