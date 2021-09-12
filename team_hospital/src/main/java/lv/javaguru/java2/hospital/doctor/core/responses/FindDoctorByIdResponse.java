package lv.javaguru.java2.hospital.doctor.core.responses;

import java.util.List;

public class FindDoctorByIdResponse extends CoreResponse {

    private boolean doctorFound;

    public FindDoctorByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindDoctorByIdResponse(boolean doctorFound) {
        this.doctorFound = doctorFound;
    }

    public boolean isDoctorFound() {
        return doctorFound;
    }
}
