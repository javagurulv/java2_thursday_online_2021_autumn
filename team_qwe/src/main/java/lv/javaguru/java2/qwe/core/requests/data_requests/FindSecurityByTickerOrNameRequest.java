package lv.javaguru.java2.qwe.core.requests.data_requests;

public class FindSecurityByTickerOrNameRequest extends CoreRequest {

    private String name;

    public FindSecurityByTickerOrNameRequest() {}

    public FindSecurityByTickerOrNameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}