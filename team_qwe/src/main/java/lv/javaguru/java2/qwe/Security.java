package lv.javaguru.java2.qwe;

import java.util.Objects;

public abstract class Security {

    private final String name;
    private final String industry;
    private final String currency;
    private double marketPrice;

    public Security(String name, String industry, String currency, double marketPrice) {
        this.name = name;
        this.industry = industry;
        this.currency = currency;
        this.marketPrice = marketPrice;
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

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Security security = (Security) o;
        return Double.compare(security.marketPrice, marketPrice) == 0
                && Objects.equals(name, security.name)
                && Objects.equals(industry, security.industry)
                && Objects.equals(currency, security.currency);
    }

}