package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables;

import java.util.List;

public class CoreResponse {

    private List<CoreError> errorsList;

    public CoreResponse() {
    }

    public CoreResponse(List<CoreError> errorsList) {
        this.errorsList = errorsList;
    }

    public List<CoreError> getErrorsList() {
        return errorsList;
    }

    public boolean hasError() {
        return errorsList != null && !errorsList.isEmpty();
    }
}
