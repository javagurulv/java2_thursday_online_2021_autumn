package lv.javaguru.java2.qwe.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_positions")
public class Position {

    @Id
    @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToOne
    @JoinColumn(name = "security_ticker", nullable = false)
    private Security security;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "purchase_price", nullable = false)
    private double purchasePrice;

    public Position() {
    }

    public Position(Security security, double amount, double purchasePrice) {
        this.security = security;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPurchasePrice(double purchasePrice) {
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
        return Double.compare(position.amount, amount) == 0
                && Double.compare(position.purchasePrice, purchasePrice) == 0
                && Objects.equals(security, position.security);
    }

}