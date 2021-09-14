package lv.javaguru.java2.qwe.core.requests.user_requests;

public class ShowUserInvestmentsByEachIndustryRequest {

    private final String userName;

    public ShowUserInvestmentsByEachIndustryRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}