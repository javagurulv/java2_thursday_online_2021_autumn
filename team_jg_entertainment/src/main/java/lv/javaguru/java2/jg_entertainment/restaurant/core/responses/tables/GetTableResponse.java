package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;

import java.util.List;

public class GetTableResponse extends CoreResponse {

    private Table table;

    public GetTableResponse(Table table) {
        this.table = table;
    }

    public GetTableResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public Table getTable() {
        return table;
    }
}
