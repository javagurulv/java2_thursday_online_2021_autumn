package lv.javaguru.java2.qwe.core.requests.user_requests;

public class GetUserPortfolioRequest {

    private String userName;

    public GetUserPortfolioRequest() {}

    public GetUserPortfolioRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}