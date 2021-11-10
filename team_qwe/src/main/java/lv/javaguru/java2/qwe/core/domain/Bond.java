package lv.javaguru.java2.qwe.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "bonds")
public class Bond extends Security {

    @Column(name = "coupon", nullable = false)
    private Double coupon;

    @Column(name = "rating", nullable = false)
    private String rating;

    @Column(name = "nominal", nullable = false)
    private Integer nominal;

    @Column(name = "maturity", nullable = false)
    private LocalDate maturity;

    public Bond() {
    }

    public Bond(String ticker, String name, String industry, String currency, Double marketPrice,
                Double coupon, String rating, Integer nominal, LocalDate maturity) {
        super(ticker, name, industry, currency, marketPrice);
        this.coupon = coupon;
        this.rating = rating;
        this.nominal = nominal;
        this.maturity = maturity;
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public void setMaturity(LocalDate maturity) {
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

    public LocalDate getMaturity() {
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