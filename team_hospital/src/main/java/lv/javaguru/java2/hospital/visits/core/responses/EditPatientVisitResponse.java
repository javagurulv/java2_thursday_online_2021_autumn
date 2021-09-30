package lv.javaguru.java2.hospital.visits.core.responses;

import java.util.List;

public class EditPatientVisitResponse extends CoreResponse {

    private boolean visitEdited;

    public EditPatientVisitResponse(List<CoreError> errors) {
        super(errors);
    }

    public EditPatientVisitResponse(boolean visitEdited) {
        this.visitEdited = visitEdited;
    }

    public boolean isVisitEdited() {
        return visitEdited;
    }
}
