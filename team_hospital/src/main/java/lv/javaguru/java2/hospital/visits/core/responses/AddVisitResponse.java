package lv.javaguru.java2.hospital.visits.core.responses;

import lv.javaguru.java2.hospital.domain.Visit;

import java.util.List;

public class AddVisitResponse extends CoreResponse {

    private Visit visit;

    public AddVisitResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddVisitResponse(Visit visit) {
        this.visit = visit;
    }

    public Visit getPatientVisit() {
        return visit;
    }
}
