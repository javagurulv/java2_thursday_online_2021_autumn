package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;

import java.util.List;

public class UpdateTableResponse extends CoreResponse {

    private Table updateTable;

    public UpdateTableResponse(Table updateTable) {
        this.updateTable = updateTable;
    }

    public UpdateTableResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public Table getUpdateTable() {
        return updateTable;
    }
}
