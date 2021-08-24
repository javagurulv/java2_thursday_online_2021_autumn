package lv.javaguru.java2.qwe;

import java.util.Objects;

class Stock extends Security {

    private final double dividends;
    private final double riskWeight;

    public Stock(String name, String industry, String currency,
                 double marketPrice, double dividends, double riskWeight) {
        super(name, industry, currency, marketPrice);
        this.dividends = dividends;
        this.riskWeight = riskWeight;
    }

    public double getDividends() {
        return dividends;
    }

    public double getRiskWeight() {
        return riskWeight;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name=" + this.getName() +
                ", industry=" + this.getIndustry() +
                ", currency=" + this.getCurrency() +
                ", marketPrice=" + this.getMarketPrice() +
                ", dividends=" + dividends +
                ", riskWeight=" + riskWeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Stock stock = (Stock) o;
        return Double.compare(stock.dividends, dividends) == 0
                && Objects.equals(riskWeight, stock.riskWeight);
    }

}