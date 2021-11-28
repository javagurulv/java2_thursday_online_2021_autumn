package lv.javaguru.java2.oddJobs.core.responce.get;

import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import lv.javaguru.java2.oddJobs.core.responce.CoreResponse;

import java.util.List;

public class GetAllSpecialistsResponse extends CoreResponse {

    private List<Specialist> specialists;

    public GetAllSpecialistsResponse(List<Specialist> specialists) {
        this.specialists = specialists;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }
}
