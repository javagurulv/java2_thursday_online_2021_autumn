package lv.javaguru.java2.qwe.core.requests.user_requests;

public class ShowUserPortfolioSummaryRequest {

    private final String userName;

    public ShowUserPortfolioSummaryRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}