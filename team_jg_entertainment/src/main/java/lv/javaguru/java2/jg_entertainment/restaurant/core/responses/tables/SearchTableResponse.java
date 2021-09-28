package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;

import java.util.List;

public class SearchTableResponse extends CoreResponse{

    private List<Table>tables;

    public SearchTableResponse(List<CoreError> errorsList, List<Table> tables) {
        super(errorsList);
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }
}
