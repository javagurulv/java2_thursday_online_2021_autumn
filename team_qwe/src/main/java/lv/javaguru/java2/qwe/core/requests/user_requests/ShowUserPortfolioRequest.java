package lv.javaguru.java2.qwe.core.requests.user_requests;

public class ShowUserPortfolioRequest {

    private final String userName;

    public ShowUserPortfolioRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}