package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;
import java.util.Map;

public class GetUserInvestmentsByEachIndustryResponse extends CoreResponse {

    private Map<String, Double> investmentMap;

    public GetUserInvestmentsByEachIndustryResponse(Map<String, Double> investmentMap) {
        this.investmentMap = investmentMap;
    }

    public GetUserInvestmentsByEachIndustryResponse(List<CoreError> errors) {
        super(errors);
    }

    public Map<String, Double> getInvestmentMap() {
        return investmentMap;
    }

}