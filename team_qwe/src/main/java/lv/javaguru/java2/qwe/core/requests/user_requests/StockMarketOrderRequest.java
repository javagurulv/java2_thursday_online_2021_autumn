package lv.javaguru.java2.qwe.core.requests.user_requests;

import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;

public class StockMarketOrderRequest extends CoreRequest {

    private User user;
    private Security security;
    private String quantity;
    private Double realTimePrice;

    public StockMarketOrderRequest() {}

    public StockMarketOrderRequest(User user, Security security, String quantity, Double realTimePrice) {
        this.user = user;
        this.security = security;
        this.quantity = quantity;
        this.realTimePrice = realTimePrice;
    }

    public User getUser() {
        return user;
    }

    public Security getSecurity() {
        return security;
    }

    public String getQuantity() {
        return quantity;
    }

    public Double getRealTimePrice() {
        return realTimePrice;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setRealTimePrice(Double realTimePrice) {
        this.realTimePrice = realTimePrice;
    }

}