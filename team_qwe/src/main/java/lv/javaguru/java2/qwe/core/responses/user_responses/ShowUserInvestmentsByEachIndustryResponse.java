package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;
import java.util.Map;

public class ShowUserInvestmentsByEachIndustryResponse extends CoreResponse {

    private Map<String, Double> investmentMap;

    public ShowUserInvestmentsByEachIndustryResponse(Map<String, Double> investmentMap) {
        this.investmentMap = investmentMap;
    }

    public ShowUserInvestmentsByEachIndustryResponse(List<CoreError> errors) {
        super(errors);
    }

    public Map<String, Double> getInvestmentMap() {
        return investmentMap;
    }

}