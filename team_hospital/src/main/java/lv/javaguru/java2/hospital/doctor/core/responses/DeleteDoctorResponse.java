package lv.javaguru.java2.hospital.doctor.core.responses;

import java.util.List;

public class DeleteDoctorResponse extends CoreResponse {

    private boolean doctorDeleted;

    public DeleteDoctorResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteDoctorResponse(boolean doctorDeleted) {
        this.doctorDeleted = doctorDeleted;
    }

    public boolean isDoctorDeleted() {
        return doctorDeleted;
    }
}
