package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.Position;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class GetUserPortfolioResponse extends CoreResponse {

    private final List<Position> portfolio;

    public GetUserPortfolioResponse(List<CoreError> errors, List<Position> portfolio) {
        super(errors);
        this.portfolio = portfolio;
    }

    public GetUserPortfolioResponse(List<Position> portfolio) {
        this.portfolio = portfolio;
    }

    public List<Position> getPortfolio() {
        return portfolio;
    }

}