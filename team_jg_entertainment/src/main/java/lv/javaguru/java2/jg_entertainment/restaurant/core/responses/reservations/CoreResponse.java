package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations;

import java.util.List;

public class CoreResponse {

    private List<CoreError> errorList;

    public CoreResponse() {
    }

    public CoreResponse(List<CoreError> errorList) {
        this.errorList = errorList;
    }

    public List<CoreError> getErrorList() {
        return errorList;
    }

    public boolean hasError() {
        return errorList != null
                && !errorList.isEmpty();
    }
}
