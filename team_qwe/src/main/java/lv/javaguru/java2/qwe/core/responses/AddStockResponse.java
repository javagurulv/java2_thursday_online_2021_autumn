package lv.javaguru.java2.qwe.core.responses;

import lv.javaguru.java2.qwe.Stock;

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