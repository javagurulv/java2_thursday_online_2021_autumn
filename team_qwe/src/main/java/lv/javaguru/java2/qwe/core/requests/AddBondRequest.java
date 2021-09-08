package lv.javaguru.java2.qwe.core.requests;

public class AddBondRequest extends SecurityRequest {

    private final String name;
    private final String industry;
    private final String currency;
    private final String marketPrice;
    private final String coupon;
    private final String rating;
    private final String nominal;
    private final String maturity;

    public AddBondRequest(String name, String industry, String currency, String marketPrice,
                          String coupon, String rating, String nominal, String maturity) {
        this.name = name;
        this.industry = industry;
        this.currency = currency;
        this.marketPrice = marketPrice;
        this.coupon = coupon;
        this.rating = rating;
        this.nominal = nominal;
        this.maturity = maturity;
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

    public String getCoupon() {
        return coupon;
    }

    public String getRating() {
        return rating;
    }

    public String getNominal() {
        return nominal;
    }

    public String getMaturity() {
        return maturity;
    }

}