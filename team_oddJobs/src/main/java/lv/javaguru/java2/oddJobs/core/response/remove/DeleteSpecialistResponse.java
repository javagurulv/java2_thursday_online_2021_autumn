package lv.javaguru.java2.oddJobs.core.response.remove;

import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class DeleteSpecialistResponse extends CoreResponse {
    private Specialist deletedSpecialist;

    public DeleteSpecialistResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteSpecialistResponse(Specialist deletedSpecialist) {
        this.deletedSpecialist = deletedSpecialist;
    }


}
