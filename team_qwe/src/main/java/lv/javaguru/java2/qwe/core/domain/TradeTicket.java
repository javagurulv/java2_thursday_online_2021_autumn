package lv.javaguru.java2.qwe.core.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
public class TradeTicket {

    @Id
    @Column(name = "trade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
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

    public TradeTicket() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Security getSecurity() {
        return security;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}