package lv.javaguru.java2.oddJobs.core.responce.add;

import lv.javaguru.java2.oddJobs.domain.Specialist;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.CoreResponse;

import java.util.List;

public class AddSpecialistResponse extends CoreResponse {

    private Specialist specialist;

    public AddSpecialistResponse(Specialist specialist) {
        this.specialist = specialist;
    }

    public AddSpecialistResponse(List<CoreError> errors) {
        super(errors);

    }
    public Specialist getSpecialist() {
        return specialist;
    }
}
