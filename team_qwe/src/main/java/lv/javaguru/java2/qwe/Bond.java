package lv.javaguru.java2.qwe;

class Bond extends Security {

    private final Double coupon;
    private final String rating;
    private final Integer nominal;
    private final String maturity;

    public Bond(String name, String industry, String currency, Double marketPrice,
                Double coupon, String rating, Integer nominal, String maturity) {
        super(name, industry, currency, marketPrice);
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
                "name=" + this.getName() +
                ", industry=" + this.getIndustry() +
                ", currency=" + this.getCurrency() +
                ", marketPrice=" + this.getMarketPrice() +
                ", coupon=" + coupon +
                ", rating=" + rating +
                ", nominal=" + nominal +
                ", maturity=" + maturity +
                '}';
    }

}