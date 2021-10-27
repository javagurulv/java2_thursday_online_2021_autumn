package lv.javaguru.java2.hospital.prescription.core.responses;

import java.util.ArrayList;
import java.util.List;

public abstract class CoreResponse {
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
