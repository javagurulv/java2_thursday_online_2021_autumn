package lv.javaguru.java2.hospital.doctor.core.responses;

import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public class ShowAllDoctorsResponse extends CoreResponse {

    private List<Doctor> doctors;

    public ShowAllDoctorsResponse(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
