package lv.javaguru.java2.qwe.core.domain;

import java.util.Objects;

public class Bond extends Security {

    private final Double coupon;
    private final String rating;
    private final Integer nominal;
    private final String maturity;

    public Bond(String ticker, String name, String industry, String currency, Double marketPrice,
                Double coupon, String rating, Integer nominal, String maturity) {
        super(ticker, name, industry, currency, marketPrice);
        this.coupon = coupon;
        this.rating = rating;
        this.nominal = nominal;
        this.maturity = maturity;
    }

    public Double getCoupon() {
        return coupon;
    }

    public String getRating() {
        return rating;
    }

    public Integer getNominal() {
        return nominal;
    }

    public String getMaturity() {
        return maturity;
    }

    @Override
    public String toString() {
        return "Bond{" +
                "ticker=" + this.getTicker() +
                ", name=" + this.getName() +
                ", industry=" + this.getIndustry() +
                ", currency=" + this.getCurrency() +
                ", marketPrice=" + this.getMarketPrice() +
                ", coupon=" + coupon +
                ", rating=" + rating +
                ", nominal=" + nominal +
                ", maturity=" + maturity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bond bond = (Bond) o;
        return Objects.equals(coupon, bond.coupon)
                && Objects.equals(rating, bond.rating)
                && Objects.equals(nominal, bond.nominal)
                && Objects.equals(maturity, bond.maturity);
    }

}