package lv.javaguru.java2.qwe.core.requests.data_requests;

public class FilterStockByIndustryRequest extends SecurityRequest {

    private final String industry;

    public FilterStockByIndustryRequest(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

}