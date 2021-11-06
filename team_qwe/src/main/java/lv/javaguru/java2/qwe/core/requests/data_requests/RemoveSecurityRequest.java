package lv.javaguru.java2.qwe.core.requests.data_requests;

public class RemoveSecurityRequest {

    private final String ticker;

    public RemoveSecurityRequest(String name) {
        this.ticker = name;
    }

    public String getTicker() {
        return ticker;
    }

}