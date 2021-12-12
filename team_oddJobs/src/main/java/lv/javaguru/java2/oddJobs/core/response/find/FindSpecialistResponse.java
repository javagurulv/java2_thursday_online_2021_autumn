package lv.javaguru.java2.oddJobs.core.response.find;

import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class FindSpecialistResponse extends CoreResponse {

    private List<Specialist> specialists;


    public FindSpecialistResponse(List<Specialist> specialists, List<CoreError> errors) {
        super(errors);
        this.specialists = specialists;
    }


    public List<Specialist> getSpecialists() {
        return specialists;
    }
}
