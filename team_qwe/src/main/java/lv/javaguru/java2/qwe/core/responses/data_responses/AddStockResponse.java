package lv.javaguru.java2.qwe.core.responses.data_responses;

import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class AddStockResponse extends CoreResponse {

    private Stock newStock;

    public AddStockResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddStockResponse(Stock stock) {
        this.newStock = stock;
    }

    public Stock getNewStock() {
        return newStock;
    }

}