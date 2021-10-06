package lv.javaguru.java2.hospital.visit.core.responses;

import lv.javaguru.java2.hospital.domain.Visit;

import java.util.List;

public class ShowAllVisitResponse extends CoreResponse{

    private List<Visit> visits;

    public ShowAllVisitResponse(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Visit> getPatientVisits() {
        return visits;
    }
}
