package lv.javaguru.java2.hospital.visit.core.responses;

import lv.javaguru.java2.hospital.domain.Visit;

import java.util.List;

public class SearchVisitResponse extends CoreResponse {

    private List<Visit> visits;

    public SearchVisitResponse(List<Visit> visits, List<CoreError> errors) {
        super(errors);
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        return visits;
    }
}
