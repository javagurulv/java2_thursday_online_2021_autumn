package lv.javaguru.java2.qwe.core.requests.data_requests;

public class FilterStocksByIndustryRequest extends CoreRequest {

    private String industry;

    public FilterStocksByIndustryRequest() {}

    public FilterStocksByIndustryRequest(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

}