package lv.javaguru.java2.qwe.core.requests.data_requests;

public class RemoveSecurityRequest {

    private String ticker;

    public RemoveSecurityRequest() {}

    public RemoveSecurityRequest(String name) {
        this.ticker = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

}