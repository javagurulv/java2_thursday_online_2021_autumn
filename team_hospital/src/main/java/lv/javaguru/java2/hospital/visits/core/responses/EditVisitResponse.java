package lv.javaguru.java2.hospital.visits.core.responses;

import java.util.List;

public class EditVisitResponse extends CoreResponse {

    private boolean visitEdited;

    public EditVisitResponse(List<CoreError> errors) {
        super(errors);
    }

    public EditVisitResponse(boolean visitEdited) {
        this.visitEdited = visitEdited;
    }

    public boolean isVisitEdited() {
        return visitEdited;
    }
}
