package lv.javaguru.java2.qwe.core.requests.user_requests;

public class GetUserPortfolioSummaryRequest {

    private final String userName;

    public GetUserPortfolioSummaryRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}