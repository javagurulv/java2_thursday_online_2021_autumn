package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.Position;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class ShowUserPortfolioResponse extends CoreResponse {

    private final List<Position> portfolio;

    public ShowUserPortfolioResponse(List<CoreError> errors, List<Position> portfolio) {
        super(errors);
        this.portfolio = portfolio;
    }

    public ShowUserPortfolioResponse(List<Position> portfolio) {
        this.portfolio = portfolio;
    }

    public List<Position> getPortfolio() {
        return portfolio;
    }

}