package lv.javaguru.java2.qwe.core.domain;

import java.util.Objects;

public class Position {

    private final Security security;
    private final double amount;
    private final double purchasePrice;

    public Position(Security security, double amount, double purchasePrice) {
        this.security = security;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
    }

    public Security getSecurity() {
        return security;
    }

    public double getAmount() {
        return amount;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public String toString() {
        return "Position{" +
                "security=" + security +
                ", amount=" + amount +
                ", purchasePrice=" + purchasePrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(security, position.security);
    }

}