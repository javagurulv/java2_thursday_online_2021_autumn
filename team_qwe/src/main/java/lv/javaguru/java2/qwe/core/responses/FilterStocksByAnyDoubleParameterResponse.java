package lv.javaguru.java2.qwe.core.responses;

import lv.javaguru.java2.qwe.Security;

import java.util.List;

public class FilterStocksByAnyDoubleParameterResponse extends CoreResponse {

    private final List<Security> list;

    public FilterStocksByAnyDoubleParameterResponse(List<Security> list) {
        this.list = list;
    }

    public FilterStocksByAnyDoubleParameterResponse(List<CoreError> errors, List<Security> list) {
        super(errors);
        this.list = list;
    }

    public List<Security> getList() {
        return list;
    }

}