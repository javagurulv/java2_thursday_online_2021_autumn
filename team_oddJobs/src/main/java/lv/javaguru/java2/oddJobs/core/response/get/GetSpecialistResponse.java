package lv.javaguru.java2.oddJobs.core.response.get;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class GetSpecialistResponse extends CoreResponse {
    private Specialist specialist;

    public GetSpecialistResponse(Specialist specialist) {
        this.specialist=specialist;
    }

    public GetSpecialistResponse(List<CoreError> errors){
        super(errors);

    }



    public Specialist getSpecialist() {
        return specialist;
    }
}
