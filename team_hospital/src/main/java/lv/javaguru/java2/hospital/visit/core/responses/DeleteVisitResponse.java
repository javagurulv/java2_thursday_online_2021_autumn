package lv.javaguru.java2.hospital.visit.core.responses;

import java.util.List;

public class DeleteVisitResponse extends CoreResponse {
    private boolean isTrue;

    public DeleteVisitResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteVisitResponse(boolean trueOrNot) {
        this.isTrue = trueOrNot;
    }

    public boolean isTrue() {
        return isTrue;
    }
}
