package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;
import java.util.Map;

public class ShowUserPortfolioGroupedByIndustryResponse extends CoreResponse {

    private Map<String, List<String>> industryMap;

    public ShowUserPortfolioGroupedByIndustryResponse(Map<String, List<String>> industryMap) {
        this.industryMap = industryMap;
    }

    public ShowUserPortfolioGroupedByIndustryResponse(List<CoreError> errors) {
        super(errors);
    }

    public Map<String, List<String>> getIndustryMap() {
        return industryMap;
    }

}