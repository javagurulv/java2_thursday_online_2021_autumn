package lv.javaguru.java2.qwe.core.requests.user_requests;

public class GetUserPortfolioRequest {

    private final String userName;

    public GetUserPortfolioRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}