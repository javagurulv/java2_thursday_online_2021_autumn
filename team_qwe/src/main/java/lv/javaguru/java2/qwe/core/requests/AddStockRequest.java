package lv.javaguru.java2.qwe.core.requests;

public class AddStockRequest extends SecurityRequest {

    private final String name;
    private final String industry;
    private final String currency;
    private final String marketPrice;
    private final String dividends;
    private final String riskWeight;

    public AddStockRequest(String name, String industry, String currency,
                           String marketPrice, String dividends, String riskWeight) {
        this.name = name;
        this.industry = industry;
        this.currency = currency;
        this.marketPrice = marketPrice;
        this.dividends = dividends;
        this.riskWeight = riskWeight;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public String getDividends() {
        return dividends;
    }

    public String getRiskWeight() {
        return riskWeight;
    }

}