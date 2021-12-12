package lv.javaguru.java2.oddJobs.core.response.update;

import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class UpdateSpecialistResponse extends CoreResponse {
    private Specialist updatedSpecialist;

    public UpdateSpecialistResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateSpecialistResponse(Specialist updatedSpecialist) {
        this.updatedSpecialist = updatedSpecialist;
    }

    public Specialist getUpdatedSpecialist() {
        return updatedSpecialist;
    }
}
