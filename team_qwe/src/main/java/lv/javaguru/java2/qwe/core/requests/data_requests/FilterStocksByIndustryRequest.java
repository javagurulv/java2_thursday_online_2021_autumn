package lv.javaguru.java2.qwe.core.requests.data_requests;

public class FilterStocksByIndustryRequest extends CoreRequest {

    private final String industry;

    public FilterStocksByIndustryRequest(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

}