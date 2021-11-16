package lv.javaguru.java2.qwe.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Security {

    @Id
    @Column(name = "ticker", nullable = false)
    private String ticker;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "market_price", nullable = false)
    private double marketPrice;

    public Security() {
    }

    public Security(String ticker, String name, String industry, String currency, double marketPrice) {
        this.ticker = ticker;
        this.name = name;
        this.industry = industry;
        this.currency = currency;
        this.marketPrice = marketPrice;
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
        return /*Double.compare(security.marketPrice, marketPrice) == 0
                && */Objects.equals(ticker, security.ticker)
                && Objects.equals(name, security.name)
                && Objects.equals(industry, security.industry)
                && Objects.equals(currency, security.currency);
    }

}