package lv.javaguru.java2.qwe.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@org.hibernate.annotations.Immutable
@Table(name = "trades")
public class TradeTicket {

    @Id
    @Column(name = "trade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_ticker", nullable = false)
    private Security security;

    @Column(name = "trade_type", nullable = false)
    private TradeType tradeType;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "trade_price", nullable = false)
    private Double tradePrice;

    @Column(name = "trade_date", nullable = false)
    private LocalDateTime dateTime;

    public TradeTicket() {}

    public TradeTicket(User user, Security security, TradeType tradeType,
                       Double quantity, Double tradePrice, LocalDateTime dateTime) {
        this.user = user;
        this.security = security;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.tradePrice = tradePrice;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Security getSecurity() {
        return security;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "TradeTicket{" +
                "user=" + user.getName() + "(ID: " + user.getId() + ")" +
                ", security=" + security.getTicker() +
                ", tradeType=" + tradeType +
                ", quantity=" + quantity +
                ", tradePrice=" + tradePrice +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeTicket ticket = (TradeTicket) o;
        return user.getId() == ticket.user.getId()
                && Objects.equals(security, ticket.security) && tradeType == ticket.tradeType
                && Objects.equals(quantity, ticket.quantity) && Objects.equals(tradePrice, ticket.tradePrice);
    }

}