package lv.javaguru.java2.hospital.doctor.core.responses;

import lv.javaguru.java2.hospital.domain.Visit;

import java.util.List;

public class ShowDoctorVisitsResponse extends CoreResponse {

    private List<Visit> visits;

    public ShowDoctorVisitsResponse(List<Visit> visits, List<CoreError> errors) {
        super(errors);
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        return visits;
    }
}
