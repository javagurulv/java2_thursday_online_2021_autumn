package lv.javaguru.java2.oddJobs.core.response.remove;

import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class RemoveSpecialistResponse extends CoreResponse {
    private boolean specialistRemoved;

    public RemoveSpecialistResponse(boolean specialistRemoved) {
        this.specialistRemoved = specialistRemoved;
    }

    public RemoveSpecialistResponse(List<CoreError> errors){
        super(errors);
    }
    public boolean isSpecialistRemoved() {
        return specialistRemoved;
    }
}
