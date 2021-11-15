package lv.javaguru.java2.qwe.core.requests.user_requests;

public class GetUserTradesRequest {

    private final String userName;

    public GetUserTradesRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}