package lv.javaguru.java2.qwe.core.requests.data_requests;

public class FindSecurityByTickerOrNameRequest extends CoreRequest {

    private final String name;

    public FindSecurityByTickerOrNameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}