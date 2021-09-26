package lv.javaguru.java2.hospital.visits.responses;

import java.util.List;

public class DeletePatientVisitResponse extends CoreResponse {
    private boolean trueOrNot;

    public DeletePatientVisitResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeletePatientVisitResponse(boolean trueOrNot) {
        this.trueOrNot = trueOrNot;
    }

    public boolean isTrueOrNot() {
        return trueOrNot;
    }
}
