package lv.javaguru.java2.qwe.core.requests.user_requests;

public class GenerateUserPortfolioRequest {

    private final String userName;

    public GenerateUserPortfolioRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}