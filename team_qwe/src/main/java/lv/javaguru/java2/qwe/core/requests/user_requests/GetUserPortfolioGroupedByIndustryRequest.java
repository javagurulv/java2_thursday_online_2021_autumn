package lv.javaguru.java2.qwe.core.requests.user_requests;

public class GetUserPortfolioGroupedByIndustryRequest {

    private final String userName;

    public GetUserPortfolioGroupedByIndustryRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}