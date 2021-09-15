package lv.javaguru.java2.qwe.core.requests.user_requests;

public class GetUserInvestmentsByEachIndustryRequest {

    private final String userName;

    public GetUserInvestmentsByEachIndustryRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}