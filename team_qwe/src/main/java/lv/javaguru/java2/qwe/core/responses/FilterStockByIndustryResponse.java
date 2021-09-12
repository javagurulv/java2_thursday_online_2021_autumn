package lv.javaguru.java2.qwe.core.responses;

import lv.javaguru.java2.qwe.Security;

import java.util.List;

public class FilterStockByIndustryResponse extends CoreResponse {

    private List<Security> list;

    public FilterStockByIndustryResponse() {
    }

    public FilterStockByIndustryResponse(List<Security> list) {
        this.list = list;
    }

    public List<Security> getList() {
        return list;
    }

}