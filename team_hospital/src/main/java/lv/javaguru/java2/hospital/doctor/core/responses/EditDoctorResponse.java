package lv.javaguru.java2.hospital.doctor.core.responses;

import java.util.List;

public class EditDoctorResponse extends CoreResponse {

    private boolean doctorEdited;

    public EditDoctorResponse(List<CoreError> errors) {
        super(errors);
    }

    public EditDoctorResponse(boolean doctorEdited) {
        this.doctorEdited = doctorEdited;
    }

    public boolean isDoctorEdited() {
        return doctorEdited;
    }
}
