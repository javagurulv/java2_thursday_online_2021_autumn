package lv.javaguru.java2.qwe.core.requests.user_requests;

public class ShowUserPortfolioGroupedByIndustryRequest {

    private final String userName;

    public ShowUserPortfolioGroupedByIndustryRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}