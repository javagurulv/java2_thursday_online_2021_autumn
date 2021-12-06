package lv.javaguru.java2.qwe.core.requests.user_requests;

public class GetUserTradesRequest {

    private String userName;

    public GetUserTradesRequest() {}

    public GetUserTradesRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}