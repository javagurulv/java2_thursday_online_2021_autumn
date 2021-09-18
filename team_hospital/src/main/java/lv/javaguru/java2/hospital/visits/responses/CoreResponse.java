package lv.javaguru.java2.hospital.visits.responses;

import java.util.ArrayList;
import java.util.List;

public class CoreResponse {
    private List<CoreError> errors = new ArrayList<>();

    public CoreResponse() {
    }

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
