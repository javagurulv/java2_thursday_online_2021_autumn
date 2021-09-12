package lv.javaguru.java2.qwe.core.responses;

import lv.javaguru.java2.qwe.Security;

import java.util.List;

public class FilterStockByMultipleParametersResponse extends CoreResponse {

    private final List<Security> list;

    public FilterStockByMultipleParametersResponse(List<Security> list) {
        this.list = list;
    }

    public FilterStockByMultipleParametersResponse(List<CoreError> errors, List<Security> list) {
        super(errors);
        this.list = list;
    }

    public List<Security> getList() {
        return list;
    }

}