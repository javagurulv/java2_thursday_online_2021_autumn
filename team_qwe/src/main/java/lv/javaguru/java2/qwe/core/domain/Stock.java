package lv.javaguru.java2.qwe.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "stocks")
public class Stock extends Security {

    @Column(name = "dividend_yield", nullable = false)
    private double dividends;

    @Column(name = "risk_weight", nullable = false)
    private double riskWeight;

    public Stock() {
    }

    public Stock(String ticker, String name, String industry, String currency,
                 double marketPrice, double dividends, double riskWeight) {
        super(ticker, name, industry, currency, marketPrice);
        this.dividends = dividends;
        this.riskWeight = riskWeight;
    }

    public void setDividends(double dividends) {
        this.dividends = dividends;
    }

    public void setRiskWeight(double riskWeight) {
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
                "ticker=" + this.getTicker() +
                ", name=" + this.getName() +
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