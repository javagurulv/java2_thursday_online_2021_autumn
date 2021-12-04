package lv.javaguru.java2.qwe.core.requests.user_requests;

public class GenerateUserPortfolioRequest {

    private String userName;

    public GenerateUserPortfolioRequest() {}

    public GenerateUserPortfolioRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}