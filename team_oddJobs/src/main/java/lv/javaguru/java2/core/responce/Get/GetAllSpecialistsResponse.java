package lv.javaguru.java2.core.responce.Get;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.responce.CoreResponse;

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
