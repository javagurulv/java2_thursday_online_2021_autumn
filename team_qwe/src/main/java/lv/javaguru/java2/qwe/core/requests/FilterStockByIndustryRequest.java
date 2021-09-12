package lv.javaguru.java2.qwe.core.requests;

public class FilterStockByIndustryRequest extends SecurityRequest {

    private String industry;

    public FilterStockByIndustryRequest(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

}