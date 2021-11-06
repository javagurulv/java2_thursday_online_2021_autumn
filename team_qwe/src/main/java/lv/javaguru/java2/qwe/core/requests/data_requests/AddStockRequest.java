package lv.javaguru.java2.qwe.core.requests.data_requests;

public class AddStockRequest extends CoreRequest {

    private final String ticker;
    private final String name;
    private final String industry;
    private final String currency;
    private final String marketPrice;
    private final String dividends;
    private final String riskWeight;

    public AddStockRequest(String ticker, String name, String industry, String currency,
                           String marketPrice, String dividends, String riskWeight) {
        this.ticker = ticker;
        this.name = name;
        this.industry = industry;
        this.currency = currency;
        this.marketPrice = marketPrice;
        this.dividends = dividends;
        this.riskWeight = riskWeight;
    }

    public String getTicker() {
        return ticker;
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