package lv.javaguru.java2.qwe.core.requests.data_requests;

public class AddStockRequest extends CoreRequest {

    private String ticker;
    private String name;
    private String industry;
    private String currency;
    private String marketPrice;
    private String dividends;
    private String riskWeight;

    public AddStockRequest() {}

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

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public void setDividends(String dividends) {
        this.dividends = dividends;
    }

    public void setRiskWeight(String riskWeight) {
        this.riskWeight = riskWeight;
    }
}