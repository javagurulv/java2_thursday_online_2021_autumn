package lv.javaguru.java2.hospital.visits.core.responses;

import java.util.List;

public class DeletePatientVisitResponse extends CoreResponse {
    private boolean isTrue;

    public DeletePatientVisitResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeletePatientVisitResponse(boolean trueOrNot) {
        this.isTrue = trueOrNot;
    }

    public boolean isTrue() {
        return isTrue;
    }
}
