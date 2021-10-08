package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;

import java.util.List;

public class AddTableResponse extends CoreResponse {

    private Table newTable;

    public AddTableResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public AddTableResponse(Table newTable) {
        this.newTable = newTable;
    }

    public Table getNewTable() {
        return newTable;
    }
}